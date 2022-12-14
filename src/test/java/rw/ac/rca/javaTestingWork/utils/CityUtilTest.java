package rw.ac.rca.javaTestingWork.utils;

import org.junit.Test;
import rw.ac.rca.javaTestingWork.domain.City;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class CityUtilTest {

    @Test
    public void no_weather_greater_than_40_celsius() {
        ArrayList<City> cities =  spy(ArrayList.class);

        cities.add(new City("Kigali",30));
        cities.add(new City("Kanombe",40));

        boolean result = false;
        for (City city : cities)
            if (city.getWeather() > 40)
                result = true;

        assertEquals(false, result);
    }

    @Test
    public void no_weather_less_than_10_celsius() {
        ArrayList<City> cities =  spy(ArrayList.class);

        cities.add(new City("Kigali",30));
        cities.add(new City("Kanombe",40));

        boolean result = false;
        for (City city : cities)
            if (city.getWeather() < 10)
                result = true;

        assertEquals(false, result);
    }

    @Test
    public void cities_have_musanze_and_kigali() {
        ArrayList<City> cities =  spy(ArrayList.class);

        City musanze = new City("Kigali",30);
        City kigali = new City("Musanze",40);

        cities.add(musanze);
        cities.add(kigali);

        assertThat(cities).extracting(City::getName).contains("Musanze","Kigali");
    }


    @Test
    public void testMocking() {
        ArrayList<City> arrayListMock =  mock(ArrayList.class);

        when(arrayListMock.size()).thenReturn(5);
        assertEquals(5, arrayListMock.size());

        when(arrayListMock.get(0)).thenReturn(new City("Kigali",30));
        assertEquals("Kigali", arrayListMock.get(0).getName());

        verify(arrayListMock, times(1)).get(0);

    }

//    @Test
//    public void testSpying() {
//
//        ArrayList<City> arrayListSpy =  spy(ArrayList.class);
//        arrayListSpy.add(new City("Kigali",30));
//
//        arrayListSpy.add(new City("Musanze",40));
//
//
//        when(arrayListSpy.size()).thenReturn(5);
//        assertEquals(5, arrayListSpy.size());
//
//        verify(arrayListSpy).add(new City("Musanze",40));
//    }
}
