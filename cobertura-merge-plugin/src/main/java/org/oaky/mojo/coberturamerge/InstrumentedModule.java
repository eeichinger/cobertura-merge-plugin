package org.oaky.mojo.coberturamerge;

public class InstrumentedModule {
    public String groupId;
    public String artifactId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public InstrumentedModule() {
    }

    public InstrumentedModule(String groupId, String artifactId) {
        this.groupId = groupId;
        this.artifactId = artifactId;
    }
}
