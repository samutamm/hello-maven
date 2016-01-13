package com.samutamm;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import java.lang.System;

/**
 * Määritellään mojon goaliksi lahetys:
 */
@Mojo(name = "tree")
public class TreeMojo extends AbstractMojo {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        String root = System.getProperty("user.dir");
        FilePrinter printer = new FilePrinter(getLog());
        printer.printFileNames(root + "/src/main/");
        printer.printFileNames(root + "/src/test/");
    }
}
