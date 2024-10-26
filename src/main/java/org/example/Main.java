package org.example;


import Entidades.Ciudad;
import Entidades.Persona;
import Manager.JPA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String,String> entitiesMap = new HashMap<String,String>();
        entitiesMap.put("Ciudad","C:\\Users\\Diego Correa\\Documents\\Sandy\\Software\\TallerIng1JPA\\ciudad.xlsx");
        entitiesMap.put("Persona","C:\\Users\\Diego Correa\\Documents\\Sandy\\Software\\TallerIng1JPA\\persona.xlsx");


        JPA entityManager = new JPA(entitiesMap);

        List<Persona> personaList = entityManager.getAll("Persona", Persona.class);
        List<Ciudad> ciudadList = entityManager.getAll("Ciudad", Ciudad.class);

        for (Persona persona : personaList) {
            System.out.println(persona);
        }
        for (Ciudad ciudad : ciudadList) {
            System.out.println(ciudad);
        }

    }
}