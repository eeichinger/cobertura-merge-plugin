package org.oaky.mojo.coberturamerge;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import java.io.File;

/**
 * Copy classes to current output directory.
 *
 * @goal copy-classes
 * @phase process-classes
 */
public class CopyClassesMojo extends AbstractMergeMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Instrumenting and including specified artifact dependencies");

        File instrumentedClasses = new File(outputDirectory);
        if ( !instrumentedClasses.exists() )
        {
            instrumentedClasses.mkdirs();
        }

//        InstrumentedModule instrumentArtifact = new InstrumentedModule("maven_integration_test_coverage", "sampl-app");
        for(InstrumentedModule instrumentedModule : includeModules) {
            MavenProject projectArtifact = resolveProject(instrumentedModule);
            if (projectArtifact == null) {
                throw new MojoExecutionException("no match found");
            }

            try {
                copyClasses(projectArtifact, instrumentedClasses);
            } catch (Exception e) {
                throw new MojoExecutionException("Failed copying class files" , e);
            }
        }
    }
}
