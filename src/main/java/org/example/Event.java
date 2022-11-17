package org.example;

public class Event {
    Integer eventId;
    Integer date;
    Integer month;
    Integer year;
    Integer patientId;
    Integer doctorId;

    public Event(Integer eventId, Integer date, Integer month, Integer year, Integer patientId, Integer doctorId) {
        this.eventId = eventId;
        this.date = date;
        this.month = month;
        this.year = year;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
}
