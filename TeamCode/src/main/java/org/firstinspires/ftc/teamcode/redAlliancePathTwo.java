package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red Alliance Wall Start")
public class redAlliancePathTwo extends Auto_XFactor_2026_Library{

    @Override
    public void runOpMode() {
        initHardware();
        waitForStart();
        if (opModeIsActive()){
            startShooter(241, 0, 0, 13, 1425);
            // First section, moves from the back to fire off the balls
            Hood.setPosition(1);
            //        sleep(2000);
            FWD_BWD(.5, 250);
            sleep(1000);
            RIGHT_TURN(.5, 230);
            BALLS_SHOOTING();
            FWD_BWD(.5,920);
            RIGHT_TURN(.5, 750);
            Intake.setPower(1);
            FWD_BWD(.4, 1400);
            Intake.setPower(.4);
            FWD_BWD(-.5, -1400);
            LEFT_TURN(.5, 700);
            FWD_BWD(-.5, -920);
            BALLS_SHOOTING();
            Hood.setPosition(0);
            FWD_BWD(.5, 1900);
            RIGHT_TURN(.5, 750);
        }
    }

}
