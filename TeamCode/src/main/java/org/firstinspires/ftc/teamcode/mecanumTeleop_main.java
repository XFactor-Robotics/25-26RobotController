package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

@TeleOp(name = "Teleop_X_Factor_2026")
public class mecanumTeleop_main extends LinearOpMode {

  private Servo Flicker;
  private DcMotor RightFront;
  private DcMotor RightBack;
  private DcMotor LeftFront;
  private DcMotor LeftBack;
  private DcMotor Intake;
  private DcMotor Shooting;
  private DcMotor TestShooter;

  /**
   * Describe this function...
   */
  @Override
  public void runOpMode() {
    Flicker = hardwareMap.get(Servo.class, "Flicker");
    RightFront = hardwareMap.get(DcMotor.class, "Right Front");
    RightBack = hardwareMap.get(DcMotor.class, "Right Back");
    LeftFront = hardwareMap.get(DcMotor.class, "Left Front");
    LeftBack = hardwareMap.get(DcMotor.class, "Left Back");
    Intake = hardwareMap.get(DcMotor.class, "Intake");
    Shooting = hardwareMap.get(DcMotor.class, "Shooting");
    TestShooter = hardwareMap.get(DcMotor.class, "Test Shooter");

    // Initialisation code
    Flicker.setPosition(0.01);
    Flicker.setDirection(Servo.Direction.FORWARD);
    RightFront.setDirection(DcMotor.Direction.REVERSE);
    RightBack.setDirection(DcMotor.Direction.REVERSE);
    waitForStart();
    // If the second gamepad is attached, this code will divide functionality between the
    // two gamepads. Otherwise, all responsibility will be on gamepad1 to control the robot.
    if (opModeIsActive()) {
      while (opModeIsActive()) {
        // Here's your loop
        gamePad2();
      }
    }
    telemetry.update();
  }

  /**
   * Gamepad 1 will ALWAYS control movement. If Gamepad 2 is not attached,
   * Gamepad1 can also control the entire robot through gamepad1_artifact_manager
   */
  private void gamePad1() {
    float y;
    double x;
    float rx;
    double denominator;

    // If it's not obvious, Y is the Y Axis.
    y = -gamepad1.left_stick_y;
    // Factor to deal with weird strafing
    x = gamepad1.left_stick_x * 1.1;
    rx = gamepad1.right_stick_x;
    // Denominator is the most power possible from the robot.
    // This ensures all powers maintain the same ratio, but only if one is outside the range for some odd reason[-1, 1].
    denominator = JavaUtil.maxOfList(JavaUtil.createListWith(JavaUtil.sumOfList(JavaUtil.createListWith(Math.abs(y), Math.abs(x), Math.abs(rx))), 1));
    //noinspection DuplicateExpressions
    LeftFront.setPower(0.75 * ((y + x + rx) / denominator));
    LeftFront.setPower(0.75 * ((y + x + rx) / denominator));
    LeftBack.setPower(0.75 * (((y - x) + rx) / denominator));
    RightFront.setPower(0.75 * (((y - x) - rx) / denominator));
    RightFront.setPower(0.75 * (((y - x) - rx) / denominator));
    RightBack.setPower(0.75 * (((y + x) - rx) / denominator));
    telemetry.update();
  }

  /**
   * Describe this function...
   */
  private void gamePad2() {
    if (gamepad2.dpad_up) {
      gamePad1();
      Flicker.setPosition(0.7);
      sleep(100);
      Flicker.setPosition(0.01);
      sleep(100);
    } else if (gamepad2.circle) {
      gamePad1();
      Intake.setPower(-0.6);
    } else if (gamepad2.dpad_right) {
      gamePad1();
      Shooting.setPower(0.7);
      TestShooter.setPower(-0.7);
    } else if (gamepad2.dpad_left) {
      gamePad1();
      Shooting.setPower(1);
      TestShooter.setPower(-1);
    } else if (gamepad2.square) {
      gamePad1();
      Shooting.setPower(0);
      TestShooter.setPower(0);
    } else if (gamepad2.cross) {
      gamePad1();
      Intake.setPower(-1);
    } else if (gamepad2.triangle) {
      gamePad1();
      Intake.setPower(1);
    } else {
      gamePad1();
      Intake.setPower(0);
    }
    telemetry.update();
  }
}