package org.oaky.mojo.coberturamerge;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import java.io.File;

/**
 * Attach Sources to current project
 *
 * @goal add-sources
 * @phase test
 */
public class AddSourcesMojo extends AbstractMergeMojo {

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("including specified artifact sources");

        File instrumentedClasses = new File(outputDirectory);
        if (!new File(project.getBuild().getOutputDirectory()).equals(instrumentedClasses) )
        {
            getLog().info("nothing instrumented, bypassing add-sources");
            return;
        }

        for(InstrumentedModule instrumentedModule : includeModules) {
            MavenProject projectArtifact = resolveProject(instrumentedModule);
            if (projectArtifact == null) {
                throw new MojoExecutionException("no match found");
            }

            try {
                attachSources(projectArtifact);
            } catch (Exception e) {
                throw new MojoExecutionException("Failed attaching sources" , e);
            }
        }
    }
}
