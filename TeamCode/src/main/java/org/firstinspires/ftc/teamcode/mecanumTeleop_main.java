package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

/*
The entirety of this code was written by hand by the GOAT (Eryn/CrazycatASG), but it was commented using Google's Gemini AI
*/
@TeleOp(name = "Teleop_X_Factor_2026")
public class mecanumTeleop_main extends LinearOpMode {

  // Hardware declarations
  private Servo Stopper;
  private DcMotor RightFront, RightBack, LeftFront, LeftBack;
  private DcMotor Intake, Shooting, TestShooter;
  private CRServo Hood;

  // State tracking for the Stopper servo toggle
  boolean isForward = false;
  boolean lastButtonState = false;

  @Override
  public void runOpMode() {
    // Map hardware to names configured on the Control Hub
    Stopper = hardwareMap.get(Servo.class, "Flicker");
    Hood = hardwareMap.get(CRServo.class, "Hood");
    RightFront = hardwareMap.get(DcMotor.class, "Right Front");
    RightBack = hardwareMap.get(DcMotor.class, "Right Back");
    LeftFront = hardwareMap.get(DcMotor.class, "Left Front");
    LeftBack = hardwareMap.get(DcMotor.class, "Left Back");
    Intake = hardwareMap.get(DcMotor.class, "Intake");
    Shooting = hardwareMap.get(DcMotor.class, "Shooting");
    TestShooter = hardwareMap.get(DcMotor.class, "Test Shooter");

    // Set initial states and reverse right side motors for tank/mecanum drive
    Stopper.setPosition(0);
    Stopper.setDirection(Servo.Direction.FORWARD);
    RightFront.setDirection(DcMotor.Direction.REVERSE);
    RightBack.setDirection(DcMotor.Direction.REVERSE);

    waitForStart();

    if (opModeIsActive()) {
      while (opModeIsActive()) {
        // Gamepad 2 method handles input and nested Gamepad 1 movement
        gamePad2();
      }
    }
  }

  /**
   * Handles Mecanum drive logic for Gamepad 1
   */
  private void gamePad1() {
    double y = -gamepad1.left_stick_y;  // Forward/Backward
    double x = gamepad1.left_stick_x * 1.1; // Strafing (1.1 counteracts friction)
    double rx = gamepad1.right_stick_x; // Turning

    // Calculate denominator to normalize motor power if sum exceeds 1.0
    double denominator = JavaUtil.maxOfList(JavaUtil.createListWith(
            Math.abs(y) + Math.abs(x) + Math.abs(rx), 1));

    // Apply powers with a 0.75 speed cap for better control
    LeftFront.setPower(0.75 * ((y + x + rx) / denominator));
    LeftBack.setPower(0.75 * (((y - x) + rx) / denominator));
    RightFront.setPower(0.75 * (((y - x) - rx) / denominator));
    RightBack.setPower(0.75 * (((y + x) - rx) / denominator));
  }

  /**
   * Handles attachments via Gamepad 2 and Stopper toggle via Gamepad 1
   */
  private void gamePad2() {
    // Toggle Stopper position on a single press of D-Pad Up
    if (gamepad1.dpad_up && !lastButtonState) {
      if (isForward) {
        Stopper.setPosition(-0.25);
        isForward = false;
      } else {
        Stopper.setPosition(0.25);
        isForward = true;
      }
    }
    lastButtonState = gamepad1.dpad_up; // Update toggle state

    // Mechanism Controls (Intake and Shooting)
    if (gamepad2.circle) {
      gamePad1();
      Intake.setPower(-0.6); // Slow Outtake
    } else if (gamepad2.dpad_right) {
      gamePad1();
      Shooting.setPower(0.7); // Low Speed Shoot
      TestShooter.setPower(-0.7);
    } else if (gamepad2.dpad_left) {
      gamePad1();
      Shooting.setPower(1); // Full Speed Shoot
      TestShooter.setPower(-1);
    } else if (gamepad2.square) {
      gamePad1();
      Shooting.setPower(0); // Stop Shooter
      TestShooter.setPower(0);
    } else if (gamepad2.cross) {
      gamePad1();
      Intake.setPower(-1); // Full Outtake
    } else if (gamepad2.triangle) {
      gamePad1();
      Intake.setPower(1); // Full Intake
    } else {
      gamePad1(); // Default: Keep driving, stop intake
      Intake.setPower(0);
    }
    telemetry.update();
  }
}
