package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Naming1 {
    private int id;
    private String enumber;

    @Id
    @Column
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column
    public String getEnumber() {
        return enumber;
    }

    public void setEnumber(String number) {
        this.enumber = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Naming1 naming = (Naming1) o;

        if (id != naming.id) return false;
        if (enumber != null ? !enumber.equals(naming.enumber) : naming.enumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (enumber != null ? enumber.hashCode() : 0);
        return result;
    }
}
