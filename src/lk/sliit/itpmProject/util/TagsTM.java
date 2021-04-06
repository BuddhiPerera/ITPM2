package lk.sliit.itpmProject.util;

public class TagsTM {
    private int id;
    private String tagName;
    private int tagCode;
    private String relatedTag;

    public TagsTM(int id, String tagName, int tagCode, String relatedTag) {
        this.id = id;
        this.tagName = tagName;
        this.tagCode = tagCode;
        this.relatedTag = relatedTag;
    }

    public TagsTM() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getTagCode() {
        return tagCode;
    }

    public void setTagCode(int tagCode) {
        this.tagCode = tagCode;
    }

    public String getRelatedTag() {
        return relatedTag;
    }

    public void setRelatedTag(String relatedTag) {
        this.relatedTag = relatedTag;
    }

    @Override
    public String toString() {
        return "TagsTM{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", tagCode=" + tagCode +
                ", relatedTag='" + relatedTag + '\'' +
                '}';
    }
}
