package org.usfirst.frc.team236.robot.commands.auto;

import org.usfirst.frc.team236.robot.AutoMap;
import org.usfirst.frc.team236.robot.commands.drive.MotionMagic;
import org.usfirst.frc.team236.robot.commands.drive.Turn;
import org.usfirst.frc.team236.robot.commands.garage.Grasp;
import org.usfirst.frc.team236.robot.commands.garage.Lower;
import org.usfirst.frc.team236.robot.commands.garage.Raise;
import org.usfirst.frc.team236.robot.commands.garage.Release;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftGear extends CommandGroup {

    public LeftGear() {
    	addSequential(new Grasp());
    	addSequential(new Lower());
    	addSequential(new MotionMagic(AutoMap.leftGearLeg1));
    	addSequential(new Turn(AutoMap.leftGearTurnDegrees, AutoMap.turn1Margin));
    	addSequential(new MotionMagic(AutoMap.leftGearLeg2));
    	addSequential(new Release());
    	addSequential(new MotionMagic(-AutoMap.leftGearLeg2));
    	
    	addParallel(new Raise());
    	addSequential(new Turn(-AutoMap.leftGearTurnDegrees, AutoMap.turn2Margin));
    	addSequential(new MotionMagic(AutoMap.leftGearLeg1));
    }
}
