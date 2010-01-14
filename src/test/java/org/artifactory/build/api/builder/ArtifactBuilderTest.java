package org.artifactory.build.api.builder;

import org.artifactory.build.api.Artifact;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

import java.util.Properties;

/**
 * Tests the behavior of the artifact builder class
 *
 * @author Noam Y. Tenne
 */
@Test
public class ArtifactBuilderTest {

    /**
     * Validates the artifact values when using the defaults
     */
    public void testDefaultBuild() {
        Artifact artifact = new ArtifactBuilder().build();

        assertEquals(artifact.getName(), "", "Unexpected default artifact name.");
        assertEquals(artifact.getType(), "", "Unexpected default artifact type.");
        assertEquals(artifact.getSha1(), "", "Unexpected default artifact SHA1 checksum.");
        assertEquals(artifact.getMd5(), "", "Unexpected default artifact MD5 checksum.");
        assertNotNull(artifact.getProperties(), "Default artifact properties should not be null.");
        assertTrue(artifact.getProperties().isEmpty(),
                "Default artifact properties list should not have been populated.");
    }

    /**
     * Validates the artifact values after using the builder setters
     */
    public void testBuilderSetters() {
        String name = "moo";
        String type = "bob";
        String sha1 = "pop";
        String md5 = "shmop";
        Properties properties = new Properties();

        Artifact artifact = new ArtifactBuilder().name(name).type(type).sha1(sha1).md5(md5).properties(properties).
                build();

        assertEquals(artifact.getName(), name, "Unexpected artifact ID.");
        assertEquals(artifact.getType(), type, "Unexpected artifact type.");
        assertEquals(artifact.getSha1(), sha1, "Unexpected artifact SHA1 checksum.");
        assertEquals(artifact.getMd5(), md5, "Unexpected artifact SHA1 checksum.");
        assertEquals(artifact.getProperties(), properties, "Unexpected artifact properties.");
        assertTrue(artifact.getProperties().isEmpty(), "Artifact properties list should not have been populated.");
    }

    /**
     * Validates the artifact values after using the builder add methods
     */
    public void testBuilderAddMethods() {
        String propertyKey = "key";
        String propertyValue = "value";

        Artifact artifact = new ArtifactBuilder().addProperty(propertyKey, propertyValue).build();
        assertTrue(artifact.getProperties().containsKey(propertyKey), "An artifact property should have been added.");
        assertEquals(artifact.getProperties().get(propertyKey), propertyValue, "Unexpected artifact property value.");
    }
}