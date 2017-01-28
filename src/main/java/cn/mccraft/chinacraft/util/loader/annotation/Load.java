package cn.mccraft.chinacraft.util.loader.annotation;

import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.relauncher.Side;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Load {
    /**
     * Select which state we should invoke this method.
     */
    LoaderState value() default LoaderState.PREINITIALIZATION;

    /**
     * Select which side we should invoke this method.
     */
    Side side() default Side.SERVER;
}
