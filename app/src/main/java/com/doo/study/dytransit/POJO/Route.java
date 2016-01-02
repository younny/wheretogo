package com.doo.study.dytransit.POJO;

import java.util.List;

/**
 * Created by dooyoungki on 12/30/15.
 */
public class Route {

    private String type; //car_sharing, private_bike, bike_sharing, taxi, public_transport
    private String provider;
    private List<Segment> segments;
    private Price price;
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public String getSegmentsString(){
        StringBuilder builder = new StringBuilder();

        for(Segment seg : segments){
            builder.append(seg.getTravelMode() + " > ");
        }
        return builder.toString();
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public Price getPrice() {
        if(price == null){
            return new Price(0, " ");
        }
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuilder formatted = new StringBuilder();
            formatted.append("\n\t-type: ").append(type)
                    .append("\n\t-provider: ").append(provider)
                    .append("\n\t-price: ").append(price)
                    .append("\n\t-properties: ").append(properties);
            for(Segment seg : segments){
                formatted.append("\n\t-segments: ").append(seg.toString());
            }

        return formatted.toString();
    }
}
