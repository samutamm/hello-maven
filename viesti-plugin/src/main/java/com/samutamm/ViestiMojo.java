package com.samutamm;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Määritellään mojon goaliksi lahetys:
 */
@Mojo(name = "lahetys",requiresProject = false)
public class ViestiMojo extends AbstractMojo {

    @Parameter(property = "lahetys.tervehdys", defaultValue = "Hello")
    private Object tervehdys;

    @Parameter(property = "lahetys.viesti", defaultValue = "Maven paven!")
    private Object viesti;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info(tervehdys.toString() + " " + viesti.toString());
    }
}
