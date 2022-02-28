package com.qulix.serovdo.api.entity;

import java.util.Objects;

public class Project implements Entity{
    private final Long id;
    private final String nameProject;
    private final String description;

    public Project(Long id, String name, String description) {
        this.id = id;
        this.nameProject = name;
        this.description = description;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getNameProject() {
        return nameProject;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) && Objects.equals(nameProject, project.nameProject)
                && Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameProject, description);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + nameProject + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
