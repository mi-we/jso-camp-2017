package com.zuehlke.jso.ccuz.swarm.repository.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by ccu on 08.05.2017.
 */
@Entity
@Table(name = "REST_DB_ACCESS")
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
})
public class Movie implements Serializable{
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;

        @Column(length = 40)
        private String name;

        public Movie() {
        }

        public Movie(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name + " " + id;
        }

        @Override
        public boolean equals(Object obj) {
            if (null == obj)
                return false;
            if (!(obj instanceof Movie))
                return false;
            Movie that = (Movie) obj;
            if (that.name.equals(this.name) && that.id == this.id)
                return true;
            else
                return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.id, this.name);
        }
}
