package org.usfirst.frc.team236.robot.commands.auto;

import org.usfirst.frc.team236.robot.AutoMap;
import org.usfirst.frc.team236.robot.commands.drive.MotionMagic;
import org.usfirst.frc.team236.robot.commands.garage.Grasp;
import org.usfirst.frc.team236.robot.commands.garage.Lower;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StraightMotionMagic extends CommandGroup {

    public StraightMotionMagic() {
    	addSequential(new Grasp());
    	addSequential(new Lower());
    	addSequential(new MotionMagic(AutoMap.straightGear));
    }
}