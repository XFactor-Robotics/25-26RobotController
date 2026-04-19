package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

/*
The entirety of this code was written by hand by the GOAT (Eryn/CrazycatASG), but it was (mostly) commented using Google's Gemini AI
*/
@TeleOp(name = "Teleop_X_Factor_2026")
public class mecanumTeleop_main extends LinearOpMode {

  // Hardware declarations
  private Servo Stopper;
  private DcMotor RightFront, RightBack, LeftFront, LeftBack;
  private DcMotorEx Intake, flywheelMotor1, flywheelMotor2;
  private Servo Hood;

  // State tracking for the Stopper servo toggle
  boolean isForward = false;
  boolean lastButtonState = false;

  double highVelocity = 1500;
  double lowVelocity = 1000;
  double curTargetVelocity;

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

  @Override
  public void runOpMode() {
    // Map hardware to names configured on the Control Hub
    Stopper = hardwareMap.get(Servo.class, "Stopper");
    Hood = hardwareMap.get(Servo.class, "Hood");
    RightFront = hardwareMap.get(DcMotor.class, "RightFront");
    RightBack = hardwareMap.get(DcMotor.class, "RightBack");
    LeftFront = hardwareMap.get(DcMotor.class, "LeftFront");
    LeftBack = hardwareMap.get(DcMotor.class, "LeftBack");
    flywheelMotor1 = hardwareMap.get(DcMotorEx.class, "RightShooter");
    flywheelMotor2 = hardwareMap.get(DcMotorEx.class, "LeftShooter");
    Intake = hardwareMap.get(DcMotorEx.class, "Intake");

    // Set initial states and reverse right side motors for tank/mecanum drive
    Stopper.setPosition(0);
    Stopper.setDirection(Servo.Direction.FORWARD);
    Hood.setPosition(0);
    RightFront.setDirection(DcMotor.Direction.REVERSE);
    RightBack.setDirection(DcMotor.Direction.REVERSE);

    flywheelMotor1.setDirection(DcMotorSimple.Direction.FORWARD);
    flywheelMotor2.setDirection(DcMotorSimple.Direction.REVERSE);
    flywheelMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    flywheelMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    PIDFCoefficients pidfCoefficients = new PIDFCoefficients(0, 0, 0, 0);
    flywheelMotor1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
    telemetry.addLine("Init Complete ^^ press to start");

    // Set float brake behaviour
    RightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    RightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    LeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    LeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

    waitForStart();

    if (opModeIsActive()) {
      while (opModeIsActive()) {
        // Gamepad 2 method handles input and nested Gamepad 1 movement
        gamePad1();
        gamePad2();
      }
    }
  }

  /**
   * Handles Mecanum drive logic for Gamepad 1
   */
  private void gamePad1() {
    double y = gamepad1.left_stick_y; // Forward/Backward
    double x = -gamepad1.left_stick_x * 1.1; // Strafing (1.1 counteracts friction)
    double rx = -gamepad1.right_stick_x; // Turning

    // Calculate denominator to normalize motor power if sum exceeds 1.0
    double denominator = JavaUtil.maxOfList(JavaUtil.createListWith(
            Math.abs(y) + Math.abs(x) + Math.abs(rx), 1));

    // Apply powers with a 0.75 speed cap for better control
    LeftFront.setPower(0.85 * ((y + x + rx) / denominator));
    LeftBack.setPower(0.85 * (((y - x) + rx) / denominator));
    RightFront.setPower(0.85 * (((y - x) - rx) / denominator));
    RightBack.setPower(0.85 * (((y + x) - rx) / denominator));
  }

  /**
   * Handles attachments via Gamepad 2 and Stopper toggle via Gamepad 1
   */
  private void gamePad2() {

    // Toggle Stopper position
    if (gamepad2.left_bumper) {
      gamePad1();
      Stopper.setPosition(0);
      //        isForward = false;
    } else if (gamepad2.right_bumper) {
      gamePad1();
      Stopper.setPosition(.33);
      //      isForward = false;
    }
    // Mechanism Controls (Intake and Shooting)
    else if (gamepad2.circle) {
      gamePad1();
      Intake.setPower(.5); // Slow Outtake
    } else if (gamepad2.dpad_left) {
      gamePad1();
      Hood.setPosition(1);
      // Resetting our PIDF coefficients each loop
      PIDFCoefficients pidfCoefficients = new PIDFCoefficients(0, 0, 0, 0);
      flywheelMotor1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
      flywheelMotor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
      startShooter(241, 0, 0, 13, 1500);

    } else if (gamepad2.dpad_right) {
      gamePad1();
      Hood.setPosition(0);
      // Resetting our PIDF coefficients each loop
      PIDFCoefficients pidfCoefficients = new PIDFCoefficients(0, 0, 0, 0);
      flywheelMotor1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
      flywheelMotor2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
      startShooter(150, 0, 0, 13, 1000);
    } else if (gamepad2.square) {
      gamePad1();
      flywheelMotor1.setPower(0); // Stop Shooter
      flywheelMotor2.setPower(0);
    } else if (gamepad2.cross) {
      gamePad1();
      Intake.setPower(-1); // Full Outtake
    } else if (gamepad2.triangle) {
      gamePad1();
      Intake.setPower(1); // Full Intake
    } else {
      // Default: Keep driving, stop intake
      gamePad1();
      Intake.setPower(0);
    }


    telemetry.update();
  }
}