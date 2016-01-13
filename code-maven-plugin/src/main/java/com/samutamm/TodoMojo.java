package com.samutamm;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "todo")
public class TodoMojo extends AbstractMojo {

    @Parameter(property = "todo.rows", defaultValue = "5")
    private Object rows;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Integer rowsInt = Integer.parseInt(rows.toString());
        new FilePrinter(getLog()).printTodoRows(System.getProperty("user.dir") + "/src/", rowsInt);
    }
}
