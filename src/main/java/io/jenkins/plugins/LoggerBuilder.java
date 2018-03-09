package io.jenkins.plugins;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import jenkins.tasks.SimpleBuildStep;
import org.kohsuke.stapler.DataBoundConstructor;

public class LoggerBuilder extends Builder implements SimpleBuildStep {
    private static final Logger LOGGER = Logger.getLogger(LoggerBuilder.class.getName());


    @DataBoundConstructor
    public LoggerBuilder() {
        super();
    }

    @Override
    public void perform(@Nonnull Run<?, ?> run, @Nonnull FilePath workspace, @Nonnull Launcher launcher, @Nonnull TaskListener listener) throws InterruptedException, IOException {
        printLog(Level.ALL);
        printLog(Level.CONFIG);
        printLog(Level.FINE);
        printLog(Level.FINER);
        printLog(Level.FINEST);
        printLog(Level.INFO);
        printLog(Level.SEVERE);
        printLog(Level.WARNING);
    }

    private void printLog(Level level) {
        LOGGER.log(level, "Log: " + level.getName() + " code: " + level.intValue());
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }

    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return true;
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return "Logger example";
        }
    }
}
