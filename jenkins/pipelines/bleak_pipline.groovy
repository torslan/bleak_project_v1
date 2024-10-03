pipeline {
    agent any

    environment {
        TEST_MODE = 'mock'  // Change to 'real' to run real tests
    }

    stages {
        stage('Build BLE Test Images') {
            parallel {
                stage('Build Advertising Test Image') {
                    steps {
                        script {
                            docker.build('ble-advertise-test-image', './ble_advertise_test')
                        }
                    }
                }
                stage('Build Receiving Test Image') {
                    steps {
                        script {
                            docker.build('ble-receive-test-image', './ble_receive_test')
                        }
                    }
                }
                stage('Build Sending Test Image') {
                    steps {
                        script {
                            docker.build('ble-send-test-image', './ble_sending_test')
                        }
                    }
                }
                stage('Build Sensor Test Image') {
                    steps {
                        script {
                            docker.build('ble-sensor-test-image', './ble_sensor_test')
                        }
                    }
                }
            }
        }

        stage('Run BLE Tests') {
            parallel {
                stage('Advertising Test') {
                    steps {
                        script {
                            docker.image('ble-advertise-test-image').inside {
                                if (env.TEST_MODE == 'mock') {
                                    sh 'python3 ble_mock_adv_test.py > ./results/adv_test_results.txt'
                                } else {
                                    sh 'python3 ble_adv_test.py > ./results/adv_test_results.txt'
                                }
                            }
                        }
                    }
                }

                stage('Receiving Test') {
                    steps {
                        script {
                            docker.image('ble-receive-test-image').inside {
                                if (env.TEST_MODE == 'mock') {
                                    sh 'python3 run_recv_tests_mock.py > ./results/recv_test_results.txt'
                                } else {
                                    sh 'python3 run_recv_tests.py > ./results/recv_test_results.txt'
                                }
                            }
                        }
                    }
                }

                stage('Sending Test') {
                    steps {
                        script {
                            docker.image('ble-send-test-image').inside {
                                if (env.TEST_MODE == 'mock') {
                                    sh 'python3 run_send_tests_mock.py > ./results/send_test_results.txt'
                                } else {
                                    sh 'python3 run_send_tests.py > ./results/send_test_results.txt'
                                }
                            }
                        }
                    }
                }

                stage('Sensor Data Test') {
                    steps {
                        script {
                            docker.image('ble-sensor-test-image').inside {
                                if (env.TEST_MODE == 'mock') {
                                    sh 'python3 run_sensor.tests_mock.py > ./results/sensor_test_results.txt'
                                } else {
                                    sh 'python3 run_sensor.tests.py > ./results/sensor_test_results.txt'
                                }
                            }
                        }
                    }
                }
            }
        }

        stage('Report Results') {
            steps {
                echo "Reporting results..."
                sh 'cat ./results/*_test_results.txt'
            }
        }
    }
}

