package org.oaky.mojo.coberturamerge;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.util.List;

public abstract class AbstractMergeMojo extends AbstractMojo {

    /**
     * <i>Maven Internal</i>: Project to interact with.
     *
     * @parameter expression="${session}"
     * @required
     * @readonly
     */
    protected MavenSession session;

    /**
     * <i>Maven Internal</i>: Project to interact with.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    protected MavenProject project;

    /**
     * The project artifacts to merge into this one
     * @parameter
     * @required
     */
    public InstrumentedModule[] includeModules;

    /**
     * The output directory
     * @parameter default-value="${project.build.outputDirectory}"
     */
    public String outputDirectory;

    protected void copyClasses(MavenProject projectArtifact, File instrumentedClasses) throws Exception {
        File artifactClasses = new File(projectArtifact.getBuild().getOutputDirectory());

        getLog().info("matching project " + project.getArtifactId() + ", copying classes from " + artifactClasses.getPath() + " to " + instrumentedClasses.getPath());
        FileUtils.copyDirectoryStructure( artifactClasses, instrumentedClasses);
    }

    protected void attachSources(MavenProject projectArtifact) throws Exception {
        getLog().info("matching project " + project.getArtifactId() + ", attaching sources");

        for(Object compileSourceRoot : projectArtifact.getCompileSourceRoots()) {
            getLog().info("adding compile source root " + compileSourceRoot);
            project.addCompileSourceRoot((String) compileSourceRoot);
        }
    }

    protected MavenProject resolveProject(InstrumentedModule instrumentedModule) {
        String instrumentArtifactId = instrumentedModule.groupId + ":" + instrumentedModule.artifactId;
        getLog().info("looking for project " + instrumentArtifactId);
        List<MavenProject> mavenProjects = session.getSortedProjects();
        for(MavenProject project:mavenProjects) {
            final String projectId = project.getGroupId() + ":" + project.getArtifactId();
            getLog().info("trying to match project " + projectId);
            if (instrumentArtifactId.equals(projectId) ) {
                return project;
            }
        }
        return null;
    }
}
