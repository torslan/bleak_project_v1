pipeline {
    agent any

    environment {
        TEST_MODE = 'mock'  // Change to 'real' to run real tests
    }

    stages {
        stage('Android OS Install') {
            steps {
                echo "Installing Android OS..."
                sh './android_os/android_install.sh'
            }
        }

        stage('Run Bluetooth Tests') {
            parallel {
                stage('A2DP Test') {
                    steps {
                        script {
                            if (env.TEST_MODE == 'mock') {
                                docker.image('a2dp-test-image').inside {
                                    sh './a2dp_test/run_a2dp_tests_mock.py'
                                    echo 'Mock A2DP test complete.'
                                }
                            } else {
                                docker.image('a2dp-test-image').inside {
                                    sh './a2dp_test/run_a2dp_tests.py'
                                    echo 'Real A2DP test complete.'
                                }
                            }
                        }
                    }
                }

                stage('HFP Test') {
                    steps {
                        script {
                            if (env.TEST_MODE == 'mock') {
                                docker.image('hfp-test-image').inside {
                                    sh './hfp_test/run_hfp_tests_mock.py'
                                    echo 'Mock HFP test complete.'
                                }
                            } else {
                                docker.image('hfp-test-image').inside {
                                    sh './hfp_test/run_hfp_tests.py'
                                    echo 'Real HFP test complete.'
                                }
                            }
                        }
                    }
                }

                stage('Connectivity/Disconnect Test') {
                    steps {
                        script {
                            if (env.TEST_MODE == 'mock') {
                                docker.image('conn-disc-test-image').inside {
                                    sh './connect_disconnect_test/run_conn_disc_tests_mock.py'
                                    echo 'Mock Connectivity/Disconnect test complete.'
                                }
                            } else {
                                docker.image('conn-disc-test-image').inside {
                                    sh './connect_disconnect_test/run_conn_disc_tests.py'
                                    echo 'Real Connectivity/Disconnect test complete.'
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

