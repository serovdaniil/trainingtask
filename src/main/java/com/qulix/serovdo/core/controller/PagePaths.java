package com.qulix.serovdo.core.controller;

public enum PagePaths {
    INDEX("/"),
    CREATEPROJECT("/WEB-INF/jsp/project/CreateProject.jsp"),
    UPDATEPROJECT("/WEB-INF/jsp/project/UpdateProject.jsp"),
    PROJECT("/WEB-INF/jsp/project/ProjectPage.jsp"),
    EMPLOYEE("/WEB-INF/jsp/employee/EmployeePage.jsp"),
    CREATEEMPLOYEE("/WEB-INF/jsp/employee/CreateEmployee.jsp"),
    UPDATEEMPLOYEE("/WEB-INF/jsp/employee/UpdateEmployee.jsp"),
    TASK("/WEB-INF/jsp/task/TaskPage.jsp");

    private final String path;

    PagePaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static PagePaths of(String name) {
        for (PagePaths page : values()) {
            if (page.name().equalsIgnoreCase(name)) {
                return page;
            }
        }
        return PROJECT;
    }
}
