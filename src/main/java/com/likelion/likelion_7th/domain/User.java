package com.likelion.likelion_7th.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String name;
    private String password;

    @Builder
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Subjects> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<DailyJob> dailyJobs = new ArrayList<>();

    public void addTask(Task task) {
        if (!tasks.contains(task)) {
            tasks.add(task);
        }
        task.assignUser(this);
    }

    public void addSubject(Subjects subject) {
        if (!subjects.contains(subject)) {
            subjects.add(subject);
        }
        subject.assignUser(this);
    }

    public void addDailyJob(DailyJob dailyJob) {
        if (!dailyJobs.contains(dailyJob)) {
            dailyJobs.add(dailyJob);
        }
        dailyJob.assignUser(this);
    }
}
