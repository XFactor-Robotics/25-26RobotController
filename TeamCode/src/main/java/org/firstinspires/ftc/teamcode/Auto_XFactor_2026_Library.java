package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Auto 2026_X_Factor")
public class Auto_XFactor_2026_Library extends LinearOpMode {

    private DcMotor LeftFront;
    private DcMotor LeftBack;
    private DcMotor RightFront;
    private DcMotor RightBack;
    private DcMotor Shooting;
    private DcMotor Intake;
    private Servo Flicker;

    /**
     * Describe this function...
     */
    private void rahrahrah(int POWER, int POS) {
        LeftFront.setTargetPosition(POS);
        LeftBack.setTargetPosition(POS);
        LeftFront.setPower(POWER);
        LeftBack.setPower(POWER);
        LeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (LeftFront.isBusy() || LeftBack.isBusy() || RightFront.isBusy() || RightBack.isBusy()) {
            telemetry.addData("LF", LeftFront.getCurrentPosition());
            telemetry.addData("LB", LeftBack.getCurrentPosition());
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
    private void Test_Run() {
        FWD_BWD(0.25, 1000);
        sleep(100);
        LEFT_TURN(0.25, 1005);
        sleep(100);
        RIGHT_TURN(0.25, 1005);
        sleep(100);
        RIGHT_GLIDE(0.25, 1000);
        sleep(100);
        LEFT_GLIDE(0.25, 1000);
        sleep(100);
    }

    /**
     * Describe this function...
     */
    private void RedAlliancePath2() {
        Shooting.setPower(0.6);
        // First section, moves from the back to fire off the balls
        FWD_BWD(0.4, 3500);
        sleep(50);
        RIGHT_TURN(0.4, 515);
        sleep(50);
        FWD_BWD(0.4, 700);
        BALLS_SHOOTING();
        sleep(50);
        // Second section, moves to intake more balls
        RIGHT_TURN(0.4, 515);
        sleep(50);
        RIGHT_GLIDE(0.4, 950);
        Intake.setPower(-0.6);
        FWD_BWD(0.5, 1300);
        Intake.setPower(0);
        Shooting.setPower(0.6);
        // Final section, moves and fires last balls.
        FWD_BWD(-0.5, -1500);
        // ^ when pos is -1300 is for 6 ball auto but it takes too long
        // so it has been increased to get off of the shooting line
        RIGHT_GLIDE(0.5, 800);
    }

    /**
     * Describe this function...
     */
    private void RedAlliancePath1() {
        Shooting.setPower(0.55);
        sleep(1500);
        FWD_BWD_ADVANCE(-0.2, -1180);
        sleep(100);
        Shooting.setPower(0);
        // Intake the First 3 Balls
        RIGHT_TURN(0.5, 450);
        sleep(50);
        RIGHT_GLIDE(0.5, 1080);
        sleep(50);
        Intake.setPower(-0.6);
        FWD_BWD(0.5, 1100);
        Intake.setPower(0);
        Shooting.setPower(0.55);
        FWD_BWD(-0.5, -1100);
        LEFT_GLIDE(0.4, 1000);
        LEFT_TURN(0.5, 420);
        BALLS_SHOOTING();
        RIGHT_TURN(0.5, 450);
        LEFT_GLIDE(0.5, 500);
        Shooting.setPower(0);
    }

    /**
     * This sample contains the bare minimum Blocks for any regular OpMode. The 3 blue
     * Comment Blocks show where to place Initialization code (runs once, after touching the
     * DS INIT button, and before touching the DS Start arrow), Run code (runs once, after
     * touching Start), and Loop code (runs repeatedly while the OpMode is active, namely not
     * Stopped).
     */
    @Override
    public void runOpMode() {
        LeftFront = hardwareMap.get(DcMotor.class, "Left Front");
        LeftBack = hardwareMap.get(DcMotor.class, "Left Back");
        RightFront = hardwareMap.get(DcMotor.class, "Right Front");
        RightBack = hardwareMap.get(DcMotor.class, "Right Back");
        Shooting = hardwareMap.get(DcMotor.class, "Shooting");
        Intake = hardwareMap.get(DcMotor.class, "Intake");
        Flicker = hardwareMap.get(Servo.class, "Flicker");

        // Put initialization blocks here.
        LeftFront.setPower(0);
        LeftBack.setPower(0);
        RightFront.setPower(0);
        RightBack.setPower(0);
        LeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftFront.setDirection(DcMotor.Direction.FORWARD);
        LeftBack.setDirection(DcMotor.Direction.FORWARD);
        RightFront.setDirection(DcMotor.Direction.REVERSE);
        RightBack.setDirection(DcMotor.Direction.REVERSE);
        LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Flicker.setPosition(0.01);
        telemetry.addLine("Ready to Start");
        telemetry.update();
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            rahrahrah(900, 5000);
        }
    }

    /**
     * Describe this function...
     */
    private void FWD_BWD(double POWER, int POS) {
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
        // FIRST BALL
        sleep(1000);
        Flicker.setPosition(0.75);
        sleep(110);
        Flicker.setPosition(0.01);
        sleep(110);
        // SECOND BALL
        Intake.setPower(1);
        sleep(1500);
        Flicker.setPosition(0.75);
        sleep(110);
        Flicker.setPosition(0.01);
        sleep(110);
        // THIRD BALL
        sleep(1500);
        Flicker.setPosition(0.75);
        sleep(110);
        Flicker.setPosition(0.01);
        sleep(110);
        Intake.setPower(0);
    }

    /**
     * Describe this function...
     */
    private void BlueAlliancePath1() {
        Shooting.setPower(0.55);
        sleep(1500);
        FWD_BWD_ADVANCE(-0.2, -1150);
        sleep(100);
        Shooting.setPower(0);
        // Intake the First 3 Balls
        LEFT_TURN(0.5, 405);
        sleep(50);
        LEFT_GLIDE(0.5, 1150);
        sleep(50);
        Intake.setPower(-0.6);
        FWD_BWD(0.5, 1300);
        Intake.setPower(0);
        Shooting.setPower(0.55);
        FWD_BWD(-0.5, -1300);
        RIGHT_GLIDE(0.4, 1000);
        RIGHT_TURN(0.5, 375);
        BALLS_SHOOTING();
        RIGHT_GLIDE(0.5, 500);
        Shooting.setPower(0);
    }

    /**
     * Describe this function...
     */
    private void LEFT_TURN(double POWER, int POS) {
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
    private void RIGHT_TURN(double POWER, int POS) {
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

    /**
     * Describe this function...
     */
    private void BlueAlliancePath2() {
        Shooting.setPower(0.55);
        // First section, moves from the back to fire off the balls
        FWD_BWD(0.4, 3500);
        sleep(50);
        LEFT_TURN(0.4, 515);
        sleep(50);
        FWD_BWD(0.4, 700);
        BALLS_SHOOTING();
        sleep(50);
        // Second section, moves to intake more balls
        LEFT_TURN(0.4, 515);
        sleep(50);
        LEFT_GLIDE(0.4, 970);
        Intake.setPower(-0.6);
        FWD_BWD(0.5, 1300);
        Intake.setPower(0);
        Shooting.setPower(0.55);
        // Final section, moves and fires last balls.
        FWD_BWD(-0.5, -1500);
        // ^ when pos is -1300 is for 6 ball auto but it takes too long
        // so it has been increased to get off of the shooting line
        LEFT_GLIDE(0.5, 800);
    }

    /**
     * Describe this function...
     */
    private void BALLS_SHOOTING() {
        // FIRST BALL
        sleep(1000);
        Flicker.setPosition(0.75);
        sleep(110);
        Flicker.setPosition(0.01);
        sleep(1500);
        // SECOND BALL
        Intake.setPower(-0.8);
        sleep(1500);
        Intake.setPower(0);
        Flicker.setPosition(0.75);
        sleep(110);
        Flicker.setPosition(0.01);
        sleep(1500);
        // THIRD BALL
        Intake.setPower(-0.8);
        sleep(1500);
        Intake.setPower(0);
        Flicker.setPosition(0.75);
        sleep(110);
        Flicker.setPosition(0.01);
        sleep(150);
    }

    /**
     * Describe this function...
     */
    private void FWD_BWD_ADVANCE(double POWER, int POS) {
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
            BALLS_SHOOTING();
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
    private void LEFT_GLIDE(double POWER, int POS) {
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
    private void RIGHT_GLIDE(double POWER, int POS) {
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
}
