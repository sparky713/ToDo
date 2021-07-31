package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a course with the course's code name, starting time, ending time, and professor
public class Course implements Writable {

    private String code;
    private String start;
    private String end;
    private String professor;

    // EFFECTS: makes a new course with the course code, start and end time, and professor
    public Course(String code, String startTime, String endTime, String prof) {
        this.code = code;
        start = startTime;
        end = endTime;
        professor = prof;
    }

    // getters & setters
    public String getCode() {
        return this.code;
    }

    public String getStartTime() {
        return this.start;
    }

    public String getEndTime() {
        return this.end;
    }

    public String getProfessor() {
        return this.professor;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("start", start);
        json.put("end", end);
        json.put("professor", professor);
        return json;
    }
}
