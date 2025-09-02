package com.secure.notes.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

    @Getter
    @Entity
    @Table(name = "roles")
    public class Role{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "role_id")
        private Integer roleId;

        @ToString.Exclude
        @Enumerated(EnumType.STRING)
        @Column(length = 20, name = "role_name")
        private AppRole roleName;

        @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
        @JsonBackReference
        @ToString.Exclude
        private Set<User> users = new HashSet<>();

        public Role(AppRole roleName) {
            this.roleName = roleName;
        }

        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
        }

        public void setRoleName(AppRole roleName) {
            this.roleName = roleName;
        }

        public void setUsers(Set<User> users) {
            this.users = users;
        }

        public AppRole getRoleName() {
            return roleName;
        }

        public Role() {
        }

        public Role(Integer roleId, AppRole roleName, Set<User> users) {
            this.roleId = roleId;
            this.roleName = roleName;
            this.users = users;
        }
    }

