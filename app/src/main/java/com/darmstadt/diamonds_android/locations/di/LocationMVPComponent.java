package com.darmstadt.diamonds_android.locations.di;


import com.darmstadt.diamonds_android.locations.view.LocationFragment;
import dagger.Subcomponent;
import javax.inject.Singleton;

@Singleton
@Subcomponent(modules = {LocationMVPModule.class})
public interface LocationMVPComponent {

    void inject(final LocationFragment target);

}
