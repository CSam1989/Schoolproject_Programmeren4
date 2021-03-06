package pr4.t1.kempenrust.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import pr4.t1.kempenrust.model.Klant;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.ArrayList;

@Repository
public class KlantRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //region Queries
    public ArrayList<Klant> get() {
        ArrayList<Klant> klanten=new ArrayList<>();
        Klant klant= null;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("" +
                "SELECT * FROM Klanten " );

        while(rowSet.next()) {
            klant = new Klant();

            klant.setKlantID(rowSet.getInt("KlantID"));
            klant.setVoornaam(rowSet.getString("Voornaam"));
            klant.setNaam(rowSet.getString("Naam"));
            klant.setStraat(rowSet.getString("Straat"));
            klant.setHuisnummer(rowSet.getString("Huisnummer"));
            klant.setPostcode(rowSet.getString("Postcode"));
            klant.setGemeente(rowSet.getString("Gemeente"));
            klant.setTelefoonnummer(rowSet.getString("Telefoonnummer"));
            klant.setEmail(rowSet.getString("Email"));

            klanten.add(klant);
        }

        return klanten;
    }

    public Klant getById(int Id) {
        Klant klant = null;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet
                ("SELECT * " +
                        "FROM Klanten " +
                        "WHERE Klanten.KlantID = '"+Id+"'");

        while(rowSet.next()) {
            klant = new Klant();

            klant.setKlantID(rowSet.getInt("KlantID"));
            klant.setVoornaam(rowSet.getString("Voornaam"));
            klant.setNaam(rowSet.getString("Naam"));
            klant.setStraat(rowSet.getString("Straat"));
            klant.setHuisnummer(rowSet.getString("Huisnummer"));
            klant.setPostcode(rowSet.getString("Postcode"));
            klant.setGemeente(rowSet.getString("Gemeente"));
            klant.setTelefoonnummer(rowSet.getString("Telefoonnummer"));
            klant.setEmail(rowSet.getString("Email"));

        }

        return klant;
    }
    
    public Klant getByBoekingId(int boekingID) {
        Klant klant = new Klant();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(
                "SELECT * FROM  " +
                        "boekingen INNER JOIN klanten ON boekingen.klantId = klanten.klantId " +
                        "WHERE " +
                        "boekingen.boekingId = ?"
                , boekingID);

        while (rowSet.next()) {
            klant.setNaam(rowSet.getString("naam"));
            klant.setVoornaam(rowSet.getString("voornaam"));
        }
        return klant;
    }

    public Klant getByEmail(String email) {
        Klant klant = null;
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("" +
                "SELECT * " +
                "FROM Klanten " +
                "WHERE LOWER(Email) = ?", email.toLowerCase().trim());

        while(rowSet.next()) {
            klant = new Klant();

            klant.setKlantID(rowSet.getInt("KlantID"));
            klant.setVoornaam(rowSet.getString("Voornaam"));
            klant.setNaam(rowSet.getString("Naam"));
            klant.setStraat(rowSet.getString("Straat"));
            klant.setHuisnummer(rowSet.getString("Huisnummer"));
            klant.setPostcode(rowSet.getString("Postcode"));
            klant.setGemeente(rowSet.getString("Gemeente"));
            klant.setTelefoonnummer(rowSet.getString("Telefoonnummer"));
            klant.setEmail(rowSet.getString("Email"));

        }

        return klant;
    }

    public boolean existsByEmail(String email) {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("" +
                "SELECT * " +
                "FROM Klanten " +
                "WHERE LOWER(Email) = ?", email.toLowerCase().trim());

        // false = no records in rowSet = customer doesnt exists
        // true = record in rowSet = customer exists
        return rowSet.next();
    }
    //endregion

    //region Commands
    public int create(String voornaam, String achternaam, String telefoonnummer, String email){
        String SqlInsertStatement = "" +
                "INSERT INTO Klanten (Voornaam, Naam, Telefoonnummer, Email) " +
                "VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(SqlInsertStatement, new String[] {"KlantID"});
            ps.setString(1, voornaam.trim());
            ps.setString(2, achternaam.trim());
            ps.setString(3, telefoonnummer.trim());
            ps.setString(4, email.trim());
            return ps;
        }, keyHolder);

        // returns the PK of the newly created record
        return keyHolder.getKey().intValue();
    }

    public void update(int klantId, String voornaam, String naam, String telefoonnummer, String email,
                       String straat, String huisnummer, String postcode, String gemeente){
        jdbcTemplate.update
                ("UPDATE Klanten SET Klanten.Voornaam = '"+voornaam+"' , Klanten.Naam = '"+naam+"', " +
                        "Klanten.Telefoonnummer = '"+telefoonnummer+"' , Klanten.Email = '"+email+"', Klanten.Straat = '"+straat+"', " +
                        "Klanten.Huisnummer = '"+huisnummer+"' ,Klanten.Postcode = '"+postcode+"', Klanten.Gemeente = '"+gemeente+"' " +
                        "WHERE KlantID = '"+klantId+"'");
    }

    public void delete(int klantId){
        jdbcTemplate.update("DELETE FROM Klanten WHERE KlantID =? ",klantId);
    }
    //endregion
}
