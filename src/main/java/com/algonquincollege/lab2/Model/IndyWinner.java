
package com.algonquincollege.lab2.Model;

/**
 * Model class representing an Indy 500 winner.
 */
public class IndyWinner {
    private int year;
    private String driver;
    private double averageSpeed;
    private String country;

    public IndyWinner(int year, String driver, double averageSpeed, String country) {
        this.year = year;
        this.driver = driver;
        this.averageSpeed = averageSpeed;
        this.country = country;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return the averageSpeed
     */
    public double getAverageSpeed() {
        return averageSpeed;
    }

    /**
     * @param averageSpeed the averageSpeed to set
     */
    public void setAverageSpeed(double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    

}
