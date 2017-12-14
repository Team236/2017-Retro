package org.usfirst.frc.team236.robot.commands.auto;

import org.usfirst.frc.team236.robot.AutoMap;
import org.usfirst.frc.team236.robot.commands.drive.MotionMagic;
import org.usfirst.frc.team236.robot.commands.drive.ShiftDown;
import org.usfirst.frc.team236.robot.commands.drive.Turn;
import org.usfirst.frc.team236.robot.commands.garage.Grasp;
import org.usfirst.frc.team236.robot.commands.garage.Lower;
import org.usfirst.frc.team236.robot.commands.garage.Raise;
import org.usfirst.frc.team236.robot.commands.garage.Release;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightGear extends CommandGroup {

    public RightGear() {
    	addSequential(new ShiftDown());
    	addSequential(new Grasp());
    	addSequential(new Lower());
    	addSequential(new MotionMagic(AutoMap.rightGearLeg1));
    	addSequential(new Turn(AutoMap.rightGearTurnDegrees, AutoMap.turn1Margin));
    	addSequential(new MotionMagic(AutoMap.rightGearLeg2));
    	addSequential(new Release());
    	addSequential(new MotionMagic(-AutoMap.rightGearLeg2));
    	
    	addParallel(new Raise());
    	addSequential(new Turn(-AutoMap.rightGearTurnDegrees, AutoMap.turn2Margin));
    	addSequential(new MotionMagic(AutoMap.rightGearLeg1));
    }
}
