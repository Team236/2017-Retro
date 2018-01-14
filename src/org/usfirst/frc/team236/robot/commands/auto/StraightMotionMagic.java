package org.usfirst.frc.team236.robot.commands.auto;

import org.usfirst.frc.team236.robot.AutoMap;
import org.usfirst.frc.team236.robot.commands.drive.MotionMagic;
import org.usfirst.frc.team236.robot.commands.drive.ShiftDown;
import org.usfirst.frc.team236.robot.commands.garage.Grasp;
import org.usfirst.frc.team236.robot.commands.garage.Lower;

import edu.wpi.first.wpilibj.command.CommandGroup;
import lib.commands.Test;

/**
 *
 */
public class StraightMotionMagic extends CommandGroup {

    public StraightMotionMagic() {
    	addSequential(new Test("Straight motion magic"), 1);
    	addSequential(new Grasp());
    	addSequential(new Lower());
    	addSequential(new ShiftDown());
    	addSequential(new MotionMagic(AutoMap.straightGear));
    }
}
