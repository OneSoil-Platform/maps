package com.mapbox.rctmgl.events;

import android.view.View;

import com.facebook.react.bridge.WritableMap;
import com.mapbox.geojson.Feature;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.rctmgl.events.constants.EventKeys;
import com.mapbox.rctmgl.events.constants.EventTypes;
import com.mapbox.rctmgl.utils.GeoJSONUtils;

public class FeatureDragEvent extends AbstractEvent {
    private String mEventKey;
    private Feature mFeature;
    private LatLng mPoint;

    public FeatureDragEvent(View view, String eventKey, String eventType, Feature feature, LatLng point) {
        super(view, eventType);
        mFeature = feature;
        mEventKey = eventKey;
        mPoint = point;
    }

    @Override
    public String getKey() {
        return mEventKey;
    }

    @Override
    public WritableMap getPayload() {
        WritableMap map = GeoJSONUtils.fromFeature(mFeature);
        if (mPoint != null) {
            map.putArray("point", GeoJSONUtils.fromLatLng(mPoint));
        }
        return map;
    }

    public static FeatureDragEvent makeShapeDragEvent(View view, Feature feature, LatLng point) {
        return new FeatureDragEvent(view, EventKeys.SHAPE_SOURCE_LAYER_DRAG,
                EventTypes.SHAPE_SOURCE_LAYER_DRAG, feature, point);
    }

    public static FeatureDragEvent makeShapeDragStartEvent(View view, Feature feature, LatLng point) {
        return new FeatureDragEvent(view, EventKeys.SHAPE_SOURCE_LAYER_DRAG_START,
                EventTypes.SHAPE_SOURCE_LAYER_DRAG_START, feature, point);
    }

    public static FeatureDragEvent makeShapeDragEndEvent(View view, Feature feature, LatLng point) {
        return new FeatureDragEvent(view, EventKeys.SHAPE_SOURCE_LAYER_DRAG_END,
                EventTypes.SHAPE_SOURCE_LAYER_DRAG_END, feature, point);
    }
}
