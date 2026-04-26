package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Autonomous(name = "Red Alliance Goal Start")
public class redAlliancePathOne extends Auto_XFactor_2026_Library{

    @Override
    public void runOpMode() {
        initHardware();
        waitForStart();
        if (opModeIsActive()){
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
            RIGHT_GLIDE(0.5, 1010);
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
            DIAGONAL_RIGHT_FB(-1, -3550);
            LEFT_TURN(1, 430);
            Intake.setPower(0);
            BALLS_SHOOTING();
        }
    }

}
