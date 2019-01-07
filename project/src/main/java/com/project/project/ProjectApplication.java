package com.project.project;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.*;

import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProjectApplication {

	String jsonData;
	static JSONObject jObject;
	
	public ProjectApplication() {

		try {
			URL url = new URL(
					"The URL goes here");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();

			if (responsecode != 200)
				throw new RuntimeException();

			else {

				Scanner sc = new Scanner(url.openStream());
				int i = 1;
				while (sc.hasNext())

				{

					jsonData = sc.nextLine();
					// System.out.println(jsonData);

					jObject = new JSONObject(jsonData);

					// System.out.println(jObject.get("prefectures"));

					// System.out.println(jObject.get("municipalities"));

					// JSONObject obj = (JSONObject) jObject.get("prefectures");
					System.out.println("----------------------------------------------------------------------------");
				}

			}

		} catch (Exception ex) {

		}
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);

		System.out.println(
				"------------------------------Leaving constructor----------------------------------------------");
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		SessionFactory factory = new Configuration().configure().addAnnotatedClass(Prefectures.class)
				.addAnnotatedClass(Municipalities.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		JSONArray prefecturesArr, municipalitiesArr;
		try {
			prefecturesArr = jObject.getJSONArray("prefectures");
			session.beginTransaction();
			List<Municipalities> muList;
			municipalitiesArr = jObject.getJSONArray("municipalities");

			for (int z = 0; z < prefecturesArr.length(); z++) {

				// save prefecturesArr
				String name = prefecturesArr.getJSONObject(z).getString("name");
				String id = prefecturesArr.getJSONObject(z).getString("id");
				String parentID = prefecturesArr.getJSONObject(z).getString("parentId");

				Prefectures pre = new Prefectures();
				pre.setId(Integer.valueOf(id));
				pre.setName(name);
				if (parentID == "null") {
					pre.setParentID(null);
				} else {
					pre.setParentID(Integer.valueOf(parentID));
				}
				session.save(pre);

			}

			session.getTransaction().commit();
			session.close();

			session = factory.getCurrentSession();

			session.beginTransaction();

			municipalitiesArr = jObject.getJSONArray("municipalities");

			for (int z = 0; z < municipalitiesArr.length(); z++) {

				// save municipalitiesArr
				String nameM = municipalitiesArr.getJSONObject(z).getString("name");
				String idM = municipalitiesArr.getJSONObject(z).getString("id");
				String parentIDM = municipalitiesArr.getJSONObject(z).getString("parentId");

				Prefectures tempPrefectures = session.get(Prefectures.class, Integer.valueOf(parentIDM));

				muList = tempPrefectures.getMunicipalities();

				Municipalities mu = new Municipalities();
				mu.setId(Integer.valueOf(idM));
				mu.setName(nameM);
				mu.setPrefecture(tempPrefectures);
				muList.add(mu);
				tempPrefectures.setMunicipalities(muList);
				session.save(mu);

			}

			session.getTransaction().commit();
			session.close();

			// System.out.println("Starting the 3rd part");

			session = factory.getCurrentSession();

			session.beginTransaction();

			for (int i = 0; i < prefecturesArr.length(); i++) {

				Prefectures tempPrefectures = session.get(Prefectures.class, Integer.valueOf(prefecturesArr.getJSONObject(i).getString("id")));
				System.out.println("Prefecture " +  tempPrefectures.getName());
				System.out.println("Municipalities{");
				muList = tempPrefectures.getMunicipalities();
				for (Municipalities m : muList) {
					System.out.println(
							"Id:" + m.getId() + " Name:" + m.getName() + " ParentId:" + tempPrefectures.getId());
				}
				System.out.println("} \n");
			}
			session.close();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (session != null)
				session.close();
			factory.close();
		}

	}

}
