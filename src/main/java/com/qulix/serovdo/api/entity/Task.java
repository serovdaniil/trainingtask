package com.qulix.serovdo.api.entity;

import java.sql.Date;
import java.util.Objects;

public class Task implements Entity {
    private final Long id;
    private final StatusTask status;
    private final String name;
    private final Project project;
    private final String job;
    private final Date startDate;
    private final java.sql.Date finishDate;
    private final Employee Employee;

    public Task(Long id, StatusTask status, String name, Project project,
                String job, Date startDate, Date finishDate,
                com.qulix.serovdo.api.entity.Employee employee) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.project = project;
        this.job = job;
        this.startDate = startDate;
        this.finishDate = finishDate;
        Employee = employee;
    }

    @Override
    public Long getId() {
        return id;
    }

    public StatusTask getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public Project getProject() {
        return project;
    }

    public String getJob() {
        return job;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public com.qulix.serovdo.api.entity.Employee getEmployee() {
        return Employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(status, task.status) && Objects.equals(name, task.name) && Objects.equals(project, task.project) && Objects.equals(job, task.job) && Objects.equals(startDate, task.startDate) && Objects.equals(finishDate, task.finishDate) && Objects.equals(Employee, task.Employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, name, project, job, startDate, finishDate, Employee);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", project=" + project +
                ", job='" + job + '\'' +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", Employee=" + Employee +
                '}';
    }
}
