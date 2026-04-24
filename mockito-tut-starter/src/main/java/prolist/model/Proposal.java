package prolist.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by yli on 23/02/2016.
 * Updated by yqtian for version 2.0.
 */

@DatabaseTable(tableName = "proposals")
public class Proposal extends SeriablizableEntity {
    public enum Cohort {
        HONOURS, MINOR_THESIS, BOTH
    }

    public enum ThesisType {
        MARKS_24, MARKS_18, BOTH
    }

    @DatabaseField
    private String title;

    /**
     * the supervisors' names, are comma-separated.
     * For example: "John Doe, Jane Doe"
     */
    @DatabaseField(canBeNull = false)
    private String supervisors;

    @DatabaseField
    private Cohort cohort;

    @DatabaseField
    private ThesisType thesisType;

    @DatabaseField
    private boolean previouslyOffered;

    @DatabaseField
    private String background;

    @DatabaseField
    private String aimsOutline;

    /**
     * the urls is a series of comma-separated urls enclosed by "<>".
     * For example, "<http://google.com>, <http://example.com>"
     */
    @DatabaseField
    private String urls;

    @DatabaseField
    private String references;

    @DatabaseField
    private String prerequisite;

    /**
     * the supervisors' names, are comma-separated.
     * For example: "John Doe, Jane Doe"
     */
    @DatabaseField(canBeNull = false)
    private String examiners;

    @DatabaseField(canBeNull = false)
    private int clicks;

    public Proposal() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(String supervisors) {
        this.supervisors = supervisors;
    }

    public Cohort getCohort() {
        return cohort;
    }

    public void setCohort(Cohort cohort) {
        this.cohort = cohort;
    }

    public ThesisType getThesisType() {
        return thesisType;
    }

    public void setThesisType(ThesisType thesisType) {
        this.thesisType = thesisType;
    }

    public boolean isPreviouslyOffered() {
        return previouslyOffered;
    }

    public void setPreviouslyOffered(boolean previouslyOffered) {
        this.previouslyOffered = previouslyOffered;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getAimsOutline() {
        return aimsOutline;
    }

    public void setAimsOutline(String aimsOutline) {
        this.aimsOutline = aimsOutline;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getExaminers() {
        return examiners;
    }

    public void setExaminers(String examiners) {
        this.examiners = examiners;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }
}
