package com.doubletuan.wing;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.amap.api.maps.AMap;
import com.doubletuan.wing.basic.BaseMapFragmentActivity;
import com.doubletuan.wing.basic.BasicMapActivity;
import com.doubletuan.wing.basic.CameraActivity;
import com.doubletuan.wing.basic.EventsActivity;
import com.doubletuan.wing.basic.HeatMapActivity;
import com.doubletuan.wing.basic.LayersActivity;
import com.doubletuan.wing.basic.MapOptionActivity;
import com.doubletuan.wing.basic.OpenglActivity;
import com.doubletuan.wing.basic.PoiClickActivity;
import com.doubletuan.wing.basic.ScreenShotActivity;
import com.doubletuan.wing.basic.UiSettingsActivity;
import com.doubletuan.wing.busline.BuslineActivity;
import com.doubletuan.wing.district.DistrictActivity;
import com.doubletuan.wing.geocoder.GeocoderActivity;
import com.doubletuan.wing.location.LocationGPSActivity;
import com.doubletuan.wing.location.LocationModeSourceActivity;
import com.doubletuan.wing.location.LocationNetworkActivity;
import com.doubletuan.wing.location.LocationSensorSourceActivity;
import com.doubletuan.wing.location.LocationSourceActivity;
import com.doubletuan.wing.offlinemap.OfflineMapActivity;
import com.doubletuan.wing.overlay.ArcActivity;
import com.doubletuan.wing.overlay.CircleActivity;
import com.doubletuan.wing.overlay.GroundOverlayActivity;
import com.doubletuan.wing.overlay.MarkerActivity;
import com.doubletuan.wing.overlay.NavigateArrowOverlayActivity;
import com.doubletuan.wing.overlay.PolygonActivity;
import com.doubletuan.wing.overlay.PolylineActivity;
import com.doubletuan.wing.poisearch.PoiAroundSearchActivity;
import com.doubletuan.wing.poisearch.PoiKeywordSearchActivity;
import com.doubletuan.wing.route.RouteActivity;
import com.doubletuan.wing.view.FeatureView;

/**
 * AMapV2地图demo总汇
 */
/**
 * @author lhy
 *
 */
public final class MainActivity extends ListActivity {
	private static class DemoDetails {
		private final int titleId;
		private final int descriptionId;
		private final Class<? extends android.app.Activity> activityClass;
private int int1;
private int int3;
		public DemoDetails(int titleId, int descriptionId,
				Class<? extends android.app.Activity> activityClass) {
			super();
			this.titleId = titleId;
			this.descriptionId = descriptionId;
			this.activityClass = activityClass;
		}
	}

	private static class CustomArrayAdapter extends ArrayAdapter<DemoDetails> {
		public CustomArrayAdapter(Context context, DemoDetails[] demos) {
			super(context, R.layout.feature, R.id.title, demos);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			FeatureView featureView;
			if (convertView instanceof FeatureView) {
				featureView = (FeatureView) convertView;
			} else {
				featureView = new FeatureView(getContext());
			}
			DemoDetails demo = getItem(position);
			featureView.setTitleId(demo.titleId);
			featureView.setDescriptionId(demo.descriptionId);
			return featureView;
		}
	}

	private static final DemoDetails[] demos = {
			new DemoDetails(R.string.basic_map, R.string.basic_description,
					BasicMapActivity.class),
			new DemoDetails(R.string.base_fragment_map,
					R.string.base_fragment_description,
					BaseMapFragmentActivity.class),
			new DemoDetails(R.string.camera_demo, R.string.camera_description,
					CameraActivity.class),
			new DemoDetails(R.string.events_demo, R.string.events_description,
					EventsActivity.class),
			new DemoDetails(R.string.layers_demo, R.string.layers_description,
					LayersActivity.class),
			new DemoDetails(R.string.heatmap_demo,
					R.string.heatmap_description, HeatMapActivity.class),
			new DemoDetails(R.string.poiclick_demo,
					R.string.poiclick_description, PoiClickActivity.class),
			new DemoDetails(R.string.mapOption_demo,
					R.string.mapOption_description, MapOptionActivity.class),
			new DemoDetails(R.string.screenshot_demo,
					R.string.screenshot_description, ScreenShotActivity.class),
			new DemoDetails(R.string.opengl_demo, R.string.opengl_description,
					OpenglActivity.class),
			new DemoDetails(R.string.uisettings_demo,
					R.string.uisettings_description, UiSettingsActivity.class),
			new DemoDetails(R.string.polyline_demo,
					R.string.polyline_description, PolylineActivity.class),
			new DemoDetails(R.string.polygon_demo,
					R.string.polygon_description, PolygonActivity.class),
			new DemoDetails(R.string.circle_demo, R.string.circle_description,
					CircleActivity.class),
			new DemoDetails(R.string.marker_demo, R.string.marker_description,
					MarkerActivity.class),
			new DemoDetails(R.string.arc_demo, R.string.arc_description,
					ArcActivity.class),
			new DemoDetails(R.string.groundoverlay_demo,
					R.string.groundoverlay_description,
					GroundOverlayActivity.class),
			new DemoDetails(R.string.navigatearrow_demo,
					R.string.navigatearrow_description,
					NavigateArrowOverlayActivity.class),
			new DemoDetails(R.string.geocoder_demo,
					R.string.geocoder_description, GeocoderActivity.class),
			new DemoDetails(R.string.locationsource_demo,
					R.string.locationsource_description,
					LocationSourceActivity.class),
			new DemoDetails(R.string.locationmodesource_demo,
					R.string.locationmodesource_description,
					LocationModeSourceActivity.class),
			new DemoDetails(R.string.locationSensorGPS_demo,
					R.string.locationSensorGPS_demo_description,
					LocationSensorSourceActivity.class),
			new DemoDetails(R.string.locationGPS_demo,
					R.string.locationGPS_description, LocationGPSActivity.class),
			new DemoDetails(R.string.locationNetwork_demo,
					R.string.locationNetwork_description,
					LocationNetworkActivity.class),
			new DemoDetails(R.string.poikeywordsearch_demo,
					R.string.poikeywordsearch_description,
					PoiKeywordSearchActivity.class),
			new DemoDetails(R.string.poiaroundsearch_demo,
					R.string.poiaroundsearch_description,
					PoiAroundSearchActivity.class),
			new DemoDetails(R.string.busline_demo,
					R.string.busline_description, BuslineActivity.class),
			new DemoDetails(R.string.route_demo, R.string.route_description,
					RouteActivity.class),
			new DemoDetails(R.string.offlinemap_demo,
					R.string.offlinemap_description, OfflineMapActivity.class),
			new DemoDetails(R.string.district_demo,
					R.string.district_description, DistrictActivity.class) };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		setTitle("3D地图Demo" + AMap.getVersion());
		ListAdapter adapter = new CustomArrayAdapter(
				this.getApplicationContext(), demos);
		setListAdapter(adapter);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		System.exit(0);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		DemoDetails demo = (DemoDetails) getListAdapter().getItem(position);
		startActivity(new Intent(this.getApplicationContext(),
				demo.activityClass));
	}
}
