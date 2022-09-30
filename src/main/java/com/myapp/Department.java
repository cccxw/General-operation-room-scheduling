package com.myapp;

import java.util.Set;

public class Department {
        private String name;
        private Set<Operation> operationSet;

        public Department() {
        }

        public Department(String name,Set<Operation> operationSet) {
                this.name = name;
                this.operationSet=operationSet;
        }

        @Override
        public String toString() {
                return name.toString();
        }

        // ********************************
        // Getters and setters
        // ********************************

        public String getName() {
                return name;
        }

        public Set<Operation> getOperationSet() {
                return operationSet;
        }

        }
