package ro.petrut.brailesc.springredis.model;

public class DocumentKey {
    private String id;
    private String status;
    private String user;
    private String company;

    public DocumentKey(String id, String status, String user, String company) {
        this.id = id;
        this.status = status;
        this.user = user;
        this.company = company;
    }

    public DocumentKey() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public static DocumentKey of(ExternalDocument externalDocument) {
        return new DocumentKey(
                externalDocument.getId(),
                externalDocument.getStatus(),
                externalDocument.getUser(),
                externalDocument.getCompany());
    }

    public String asKey() {
        return this.getId() + "_" + this.getStatus() + "_" + this.getUser() + "_" + this.getCompany();
    }

    @Override
    public String toString() {
        return "DocumentKey{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", user='" + user + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
