package com.qulix.serovdo.api.entity;

import java.util.Objects;

public class StatusTask implements Entity{
    private final Long id;
    private final String nameTask;

    public StatusTask(Long id, String nameTask) {
        this.id = id;
        this.nameTask = nameTask;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getNameTask() {
        return nameTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusTask that = (StatusTask) o;
        return Objects.equals(id, that.id) && Objects.equals(nameTask, that.nameTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameTask);
    }

    @Override
    public String toString() {
        return "StatusTask{" +
                "idTask=" + id +
                ", nameTask='" + nameTask + '\'' +
                '}';
    }
}
