package com.example.caffae.studentsc.OutdatedTests;

import android.arch.lifecycle.LifecycleRegistry;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManagerNonConfig;
import android.support.v4.util.SimpleArrayMap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caffae.studentsc.Grades.GradesPageFragment;
import com.google.firebase.database.DatabaseReference;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import butterknife.Unbinder;

/**
 * Created by Caffae on 4/4/18.
 */
public class GradesPageFragmentTest {
    @Mock
    DatabaseReference mDatabase;
    @Mock
    Unbinder unbinder;
    @Mock
    TextView studentIDDisplay;
    @Mock
    TextView gradeDisplay;
    @Mock
    TextView scoreDisplay;
    @Mock
    TextView individualQScores;
    @Mock
    SimpleArrayMap<String, Class<?>> sClassMap;
    @Mock
    Object USE_DEFAULT_TRANSITION;
    //Field mSavedFragmentState of type Bundle - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    SparseArray<Parcelable> mSavedViewState;
    //Field mArguments of type Bundle - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    Fragment mTarget;
    //Field mFragmentManager of type FragmentManagerImpl - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    FragmentHostCallback mHost;
    //Field mChildFragmentManager of type FragmentManagerImpl - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    FragmentManagerNonConfig mChildNonConfig;
    @Mock
    Fragment mParentFragment;
    @Mock
    ViewGroup mContainer;
    @Mock
    View mView;
    @Mock
    View mInnerView;
    @Mock
    LayoutInflater mLayoutInflater;
    @Mock
    LifecycleRegistry mLifecycleRegistry;
    @InjectMocks
    GradesPageFragment gradesPageFragment;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        gradesPageFragment = new GradesPageFragment();

    }

    @Test
    public void testOnCreate() throws Exception {
        gradesPageFragment.onCreate(null);
    }

    @Test
    public void testOnCreateView() throws Exception {
        View result = gradesPageFragment.onCreateView(null, null, null);
        Assert.assertEquals(null, result);
    }

    @Test
    public void testOnDestroyView() throws Exception {
        gradesPageFragment.onDestroyView();
    }

//    @Test
//    public void dataIsPresent() throws Exception{
////        View result = gradesPageFragment.onCreateView(null, null, null);
//
//
//        gradesPageFragment.studentId = 1000019;
//
//    }

//    @Test
//    public void databaseGet() throws Exception {
//        gradesPageFragment.
//    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme