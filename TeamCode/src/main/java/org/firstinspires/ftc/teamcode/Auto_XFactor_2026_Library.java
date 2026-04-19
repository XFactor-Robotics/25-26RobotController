    package org.firstinspires.ftc.teamcode;

    import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
    import com.qualcomm.robotcore.hardware.DcMotor;
    import com.qualcomm.robotcore.hardware.DcMotorEx;
    import com.qualcomm.robotcore.hardware.DcMotorSimple;
    import com.qualcomm.robotcore.hardware.PIDFCoefficients;
    import com.qualcomm.robotcore.hardware.Servo;


    public abstract class Auto_XFactor_2026_Library extends LinearOpMode {

        // Hardware declarations
        public Servo Stopper;
        public DcMotor RightFront, RightBack, LeftFront, LeftBack;
        public DcMotorEx Intake, flywheelMotor1, flywheelMotor2;
        public Servo Hood;

        /**
         * Describe this function...
         */

        public void startShooter(double P, double I, double D, double F, double curTargetVelocity) {

            flywheelMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            flywheelMotor2.setPIDFCoefficients(
                    DcMotor.RunMode.RUN_USING_ENCODER,
                    new PIDFCoefficients(P, I, D, F)
            );
            flywheelMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            flywheelMotor1.setPIDFCoefficients(
                    DcMotor.RunMode.RUN_USING_ENCODER,
                    new PIDFCoefficients(P, I, D, F)
            );

            // Set velocity
            flywheelMotor2.setVelocity(curTargetVelocity);
            flywheelMotor1.setVelocity(curTargetVelocity);
        }

        private void RedAlliancePath2() {
            startShooter(241, 0, 0, 13, 1450);
            // First section, moves from the back to fire off the balls
            Hood.setPosition(1);
    //        sleep(2000);
            FWD_BWD(.5, 250);
            sleep(1000);
            RIGHT_TURN(.5, 230);
            BALLS_SHOOTING();
            FWD_BWD(.5,920);
            RIGHT_TURN(.5, 735);
            Intake.setPower(1);
            FWD_BWD(.4, 1400);
            Intake.setPower(.4);
            FWD_BWD(-.5, -1400);
            LEFT_TURN(.5, 750);
            FWD_BWD(-.5, -920);
            BALLS_SHOOTING();
            Hood.setPosition(0);
            FWD_BWD(.5, 1900);
            RIGHT_TURN(.5, 750);
    //        Intake.setPower(1);
    //        FWD_BWD(.4, 1200);
    //        Intake.setPower(.3);
    //        DIAGONAL_RIGHT_FB(-1, -3400);
    //        Hood.setPosition(.85);
    //        LEFT_TURN(1, 425);
    //        BALLS_SHOOTING();
    //        sleep(2000);
        }

        /**
         * Describe this function...
         */
        private void RedAlliancePath1() {
            Intake.setPower(.4);
            PIDFCoefficients pidfCoefficients = new PIDFCoefficients(0,0,0,0);
            flywheelMotor1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
            flywheelMotor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
            startShooter(150, 0, 0, 13, 1000);
            // First set
            FWD_BWD(.5, -1200);
            BALLS_SHOOTING();
            // Intake the second set
            RIGHT_TURN(.6, 460);
            RIGHT_GLIDE(0.5, 1110);
            Intake.setPower(1);
            FWD_BWD(0.4, 1100);
            Intake.setPower(0.4);
            // Shoot the second set
            DIAGONAL_RIGHT_FB(-.8, -1900);
            LEFT_TURN(.6, 450);
            Intake.setPower(0);
            BALLS_SHOOTING();
            // Go for the third set
            RIGHT_TURN(0.6, 480);
            RIGHT_GLIDE(0.5, 2020);
            Intake.setPower(1);
            FWD_BWD(0.4, 1000);
            Intake.setPower(0.4);
            startShooter(180, 0, 0, 13, 1100);
            Hood.setPosition(.65);
            DIAGONAL_RIGHT_FB(-1, -3500);
            LEFT_TURN(1, 430);
            Intake.setPower(0);
            BALLS_SHOOTING();
        }


        void BALLS_SHOOTING() {
            Stopper.setPosition(.33);
            sleep(150);
            Intake.setPower(0.5);
            sleep(2000);
            Intake.setPower(0.4);
            Stopper.setPosition(0);
        }

        /**
         * This sample contains the bare minimum Blocks for any regular OpMode. The 3 blue
         * Comment Blocks show where to place Initialization code (runs once, after touching the
         * DS INIT button, and before touching the DS Start arrow), Run code (runs once, after
         * touching Start), and Loop code (runs repeatedly while the OpMode is active, namely not
         * Stopped).
         */

        public void initHardware() {
            LeftFront = hardwareMap.get(DcMotor.class, "LeftFront");
            LeftBack = hardwareMap.get(DcMotor.class, "LeftBack");
            RightFront = hardwareMap.get(DcMotor.class, "RightFront");
            RightBack = hardwareMap.get(DcMotor.class, "RightBack");
            Stopper = hardwareMap.get(Servo.class, "Stopper");
            Hood = hardwareMap.get(Servo.class, "Hood");
            flywheelMotor1 = hardwareMap.get(DcMotorEx.class, "RightShooter");
            flywheelMotor2 = hardwareMap.get(DcMotorEx.class, "LeftShooter");
            Intake = hardwareMap.get(DcMotorEx.class, "Intake");


            // Put initialization blocks here.
            LeftFront.setPower(0);
            LeftBack.setPower(0);
            RightFront.setPower(0);
            RightBack.setPower(0);
            LeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LeftFront.setDirection(DcMotor.Direction.REVERSE);
            LeftBack.setDirection(DcMotor.Direction.REVERSE);
            RightFront.setDirection(DcMotor.Direction.FORWARD);
            RightBack.setDirection(DcMotor.Direction.FORWARD);
            LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Stopper.setPosition(0);
            Hood.setPosition(0);
            flywheelMotor1.setDirection(DcMotorSimple.Direction.FORWARD);
            flywheelMotor2.setDirection(DcMotorSimple.Direction.REVERSE);
            telemetry.addLine("Ready to Start :P");
            telemetry.update();
        }

        /**
         * Describe this function...
         */

        void FWD_BWD(double POWER, int POS) {
            LeftFront.setTargetPosition(POS);
            LeftBack.setTargetPosition(POS);
            RightFront.setTargetPosition(POS);
            RightBack.setTargetPosition(POS);
            LeftFront.setPower(POWER);
            LeftBack.setPower(POWER);
            RightFront.setPower(POWER);
            RightBack.setPower(POWER);
            LeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (LeftFront.isBusy() || LeftBack.isBusy() || RightFront.isBusy() || RightBack.isBusy()) {
                telemetry.addData("LF", LeftFront.getCurrentPosition());
                telemetry.addData("LB", LeftBack.getCurrentPosition());
                telemetry.addData("RF", RightFront.getCurrentPosition());
                telemetry.addData("RB", RightBack.getCurrentPosition());
                telemetry.update();
            }
            LeftFront.setPower(0);
            LeftBack.setPower(0);
            RightFront.setPower(0);
            RightBack.setPower(0);
            LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            POS = 0;
            POWER = 0;
        }

        /**
         * Describe this function...
         */
        private void BALLS_SHOOTING_ADVANCE() {
            Stopper.setPosition(0);
            sleep(3000);
            Intake.setPower(0);
        }

        /**
         * Describe this function...
         */
        private void BlueAlliancePath1() {
            Intake.setPower(.4);
            PIDFCoefficients pidfCoefficients = new PIDFCoefficients(0,0,0,0);
            flywheelMotor1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
            flywheelMotor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
            startShooter(150, 0, 0, 13, 1000);
            // First set
            FWD_BWD(.5, -1200);
            BALLS_SHOOTING();
            // Intake the second set
            LEFT_TURN(.6, 360);
            LEFT_GLIDE(0.5, 1270);
            Intake.setPower(1);
            FWD_BWD(0.4, 1100);
            Intake.setPower(0.4);
            // Shoot the second set
            DIAGONAL_LEFT_FB(-.8, -1920);
            RIGHT_TURN(.6, 450);
            BALLS_SHOOTING();
            // Go for the third set
            LEFT_TURN(0.6, 470);
            LEFT_GLIDE(0.5, 2045);
            Intake.setPower(1);
            FWD_BWD(0.4, 1000);
            Intake.setPower(0.4);
            startShooter(180, 0, 0, 13, 1100);
            Hood.setPosition(.65);
            DIAGONAL_LEFT_FB(-1, -3450);
            RIGHT_TURN(1, 430);
            BALLS_SHOOTING();
        }

        /**
         * Describe this function...
         */
        void LEFT_TURN(double POWER, int POS) {
            LeftFront.setTargetPosition(POS * -1);
            LeftBack.setTargetPosition(POS * -1);
            RightFront.setTargetPosition(POS);
            RightBack.setTargetPosition(POS);
            LeftFront.setPower(POWER * -1);
            LeftBack.setPower(POWER * -1);
            RightFront.setPower(POWER);
            RightBack.setPower(POWER);
            LeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (LeftFront.isBusy() || LeftBack.isBusy() || RightFront.isBusy() || RightBack.isBusy()) {
                telemetry.addData("LF", LeftFront.getCurrentPosition());
                telemetry.addData("LB", LeftBack.getCurrentPosition());
                telemetry.addData("RF", RightFront.getCurrentPosition());
                telemetry.addData("RF", RightBack.getCurrentPosition());
                telemetry.update();
            }
            LeftFront.setPower(0);
            LeftBack.setPower(0);
            RightFront.setPower(0);
            RightBack.setPower(0);
            LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            POS = 0;
            POWER = 0;
        }

        /**
         * Describe this function...
         */
        void RIGHT_TURN(double POWER, int POS) {
            LeftFront.setTargetPosition(POS);
            LeftBack.setTargetPosition(POS);
            RightFront.setTargetPosition(POS * -1);
            RightBack.setTargetPosition(POS * -1);
            LeftFront.setPower(POWER);
            LeftBack.setPower(POWER);
            RightFront.setPower(POWER * -1);
            RightBack.setPower(POWER * -1);
            LeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (LeftFront.isBusy() || LeftBack.isBusy() || RightFront.isBusy() || RightBack.isBusy()) {
                telemetry.addData("LF", LeftFront.getCurrentPosition());
                telemetry.addData("LB", LeftBack.getCurrentPosition());
                telemetry.addData("RF", RightFront.getCurrentPosition());
                telemetry.addData("RF", RightBack.getCurrentPosition());
                telemetry.update();
            }
            LeftFront.setPower(0);
            LeftBack.setPower(0);
            RightFront.setPower(0);
            RightBack.setPower(0);
            LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            POS = 0;
            POWER = 0;
        }

        private void BlueAlliancePath2() {
            startShooter(241, 0, 0, 13, 1450);
            // First section, moves from the back to fire off the balls
            Hood.setPosition(1);
            //        sleep(2000);
            FWD_BWD(.5, 250);
            sleep(1000);
            LEFT_TURN(.5, 230);
            BALLS_SHOOTING();
            FWD_BWD(.5,920);
            LEFT_TURN(.5, 735);
            Intake.setPower(1);
            FWD_BWD(.4, 1400);
            Intake.setPower(.4);
            FWD_BWD(-.5, -1400);
            RIGHT_TURN(.5, 750);
            FWD_BWD(-.5, -920);
            BALLS_SHOOTING();
            Hood.setPosition(0);
            FWD_BWD(.5, 1900);
            LEFT_TURN(.5, 750);
        }

    //    private void BALLS_SHOOTING() {
    //        // FIRST BALL
    //        sleep(1000);
    //        Flicker.setPosition(0.75);
    //        sleep(110);
    //        Flicker.setPosition(0.01);
    //        sleep(1500);
    //        // SECOND BALL
    //        Intake.setPower(-0.8);
    //        sleep(1500);
    //        Intake.setPower(0);
    //        Flicker.setPosition(0.75);
    //        sleep(110);
    //        Flicker.setPosition(0.01);
    //        sleep(1500);
    //        // THIRD BALL
    //        Intake.setPower(-0.8);
    //        sleep(1500);
    //        Intake.setPower(0);
    //        Flicker.setPosition(0.75);
    //        sleep(110);
    //        Flicker.setPosition(0.01);
    //        sleep(150);
    //    }

        /**
         * Describe this function...
         */
    //    private void FWD_BWD_ADVANCE(double POWER, int POS) {
    //        LeftFront.setTargetPosition(POS);
    //        LeftBack.setTargetPosition(POS);
    //        RightFront.setTargetPosition(POS);
    //        RightBack.setTargetPosition(POS);
    //        LeftFront.setPower(POWER);
    //        LeftBack.setPower(POWER);
    //        RightFront.setPower(POWER);
    //        RightBack.setPower(POWER);
    //        LeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    //        LeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    //        RightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    //        RightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    //        while (LeftFront.isBusy() || LeftBack.isBusy() || RightFront.isBusy() || RightBack.isBusy()) {
    //            BALLS_SHOOTING();
    //        }
    //        LeftFront.setPower(0);
    //        LeftBack.setPower(0);
    //        RightFront.setPower(0);
    //        RightBack.setPower(0);
    //        LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    //        LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    //        RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    //        RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    //        POS = 0;
    //        POWER = 0;
    //    }

        /**
         * Describe this function...
         */
        void LEFT_GLIDE(double POWER, int POS) {
            LeftFront.setTargetPosition(POS * -1);
            LeftBack.setTargetPosition(POS);
            RightFront.setTargetPosition(POS);
            RightBack.setTargetPosition(POS * -1);
            LeftFront.setPower(POWER * -1);
            LeftBack.setPower(POWER);
            RightFront.setPower(POWER);
            RightBack.setPower(POWER * -1);
            LeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (LeftFront.isBusy() || LeftBack.isBusy() || RightFront.isBusy() || RightBack.isBusy()) {
                telemetry.addData("LF", LeftFront.getCurrentPosition());
                telemetry.addData("LB", LeftBack.getCurrentPosition());
                telemetry.addData("RF", RightFront.getCurrentPosition());
                telemetry.addData("RF", RightBack.getCurrentPosition());
                telemetry.update();
            }
            LeftFront.setPower(0);
            LeftBack.setPower(0);
            RightFront.setPower(0);
            RightBack.setPower(0);
            LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            POS = 0;
            POWER = 0;
        }

        /**
         * Describe this function...
         */
        void RIGHT_GLIDE(double POWER, int POS) {
            LeftFront.setTargetPosition(POS);
            LeftBack.setTargetPosition(POS * -1);
            RightFront.setTargetPosition(POS * -1);
            RightBack.setTargetPosition(POS);
            LeftFront.setPower(POWER);
            LeftBack.setPower(POWER * -1);
            RightFront.setPower(POWER * -1);
            RightBack.setPower(POWER);
            LeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (LeftFront.isBusy() || LeftBack.isBusy() || RightFront.isBusy() || RightBack.isBusy()) {
                telemetry.addData("LF", LeftFront.getCurrentPosition());
                telemetry.addData("LB", LeftBack.getCurrentPosition());
                telemetry.addData("RF", RightFront.getCurrentPosition());
                telemetry.addData("RF", RightBack.getCurrentPosition());
                telemetry.update();
            }
            LeftFront.setPower(0);
            LeftBack.setPower(0);
            RightFront.setPower(0);
            RightBack.setPower(0);
            LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            POS = 0;
            POWER = 0;
        }


        void DIAGONAL_RIGHT_FB(double POWER, int POS) {
            LeftFront.setTargetPosition(POS);
            RightBack.setTargetPosition(POS);
            LeftBack.setTargetPosition(-10);
            RightFront.setTargetPosition(-10);
            LeftFront.setPower(POWER);
            RightBack.setPower(POWER);
            RightFront.setPower(POWER);
            LeftBack.setPower(POWER);
            LeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (LeftFront.isBusy() || LeftBack.isBusy() || RightFront.isBusy() || RightBack.isBusy()) {
                telemetry.addData("LF", LeftFront.getCurrentPosition());
                telemetry.addData("RF", RightFront.getCurrentPosition());
                telemetry.addData("RB", RightBack.getCurrentPosition());
                telemetry.addData("LB", LeftBack.getCurrentPosition());
                telemetry.update();
            }
            LeftFront.setPower(0);
            LeftBack.setPower(0);
            RightFront.setPower(0);
            RightBack.setPower(0);
            LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            POS = 0;
            POWER = 0;
        }
        void DIAGONAL_LEFT_FB(double POWER, int POS) {
            RightFront.setTargetPosition(POS);
            LeftBack.setTargetPosition(POS);
            RightBack.setTargetPosition(-10);
            LeftFront.setTargetPosition(-10);
            RightFront.setPower(POWER);
            LeftBack.setPower(POWER);
            LeftFront.setPower(POWER);
            RightBack.setPower(POWER);
            LeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            while (LeftFront.isBusy() || LeftBack.isBusy() || RightFront.isBusy() || RightBack.isBusy()) {
                telemetry.addData("LF", LeftFront.getCurrentPosition());
                telemetry.addData("RF", RightFront.getCurrentPosition());
                telemetry.addData("RB", RightBack.getCurrentPosition());
                telemetry.addData("LB", LeftBack.getCurrentPosition());
                telemetry.update();
            }
            LeftFront.setPower(0);
            LeftBack.setPower(0);
            RightFront.setPower(0);
            RightBack.setPower(0);
            LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            POS = 0;
            POWER = 0;
        }


    }
