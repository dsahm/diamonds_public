package com.darmstadt.diamonds_android.dates.di;


import com.darmstadt.diamonds_android.dates.view.DatesFragment;
import dagger.Subcomponent;
import javax.inject.Singleton;

@Singleton
@Subcomponent(modules = {DatesMVPModule.class})
public interface DatesMVPComponent {

    void inject(final DatesFragment target);

}
