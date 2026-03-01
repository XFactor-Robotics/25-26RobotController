package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

@TeleOp(name = "mecanumTeleop_main")
public class mecanumTeleop_main extends LinearOpMode {

  private Servo Flicker;
  private DcMotor RightFront;
  private DcMotor RightBack;
  private DcMotor LeftFront;
  private DcMotor LeftBack;
  private DcMotor Shooting;
  private DcMotor Intake;

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
    Shooting = hardwareMap.get(DcMotor.class, "Shooting");
    Intake = hardwareMap.get(DcMotor.class, "Intake");

    // Initialisation code
    Flicker.setPosition(0.01);
    Flicker.setDirection(Servo.Direction.FORWARD);
    RightFront.setDirection(DcMotor.Direction.REVERSE);
    RightBack.setDirection(DcMotor.Direction.REVERSE);
    waitForStart();
    // Both gamepads share responsibility to control the robot. If one is missing, it can't run.
    if (opModeIsActive()) {
      while (opModeIsActive()) {
        // Here's your loop
        gamePad1();
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
    double denominator;
    double x;
    float rx;

    // If it's not obvious, Y is the Y Axis.
    y = -gamepad1.left_stick_y;
    // Factor to deal with weird strafing
    x = gamepad1.left_stick_x * 1.1;
    rx = gamepad1.right_stick_x;
    // Denominator is the most power possible from the robot.
    // This ensures all powers maintain the same ratio, but only if one is outside of the range for some odd reason[-1, 1].
    denominator = JavaUtil.maxOfList(JavaUtil.createListWith(JavaUtil.sumOfList(JavaUtil.createListWith(Math.abs(y), Math.abs(x), Math.abs(rx))), 1));
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
      Flicker.setPosition(0.7);
      sleep(100);
      Flicker.setPosition(0.01);
      sleep(1000);
    } else if (gamepad2.circle) {
      Shooting.setPower(0.65);
    } else if (gamepad2.dpad_right) {
      Shooting.setPower(1);
    } else if (gamepad2.square) {
      Shooting.setPower(0);
    } else if (gamepad2.cross) {
      Intake.setPower(-1);
    } else if (gamepad2.triangle) {
      Intake.setPower(1);
    } else {
      Intake.setPower(0);
    }
    telemetry.update();
  }
}
