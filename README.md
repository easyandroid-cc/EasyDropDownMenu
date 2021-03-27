EasyDropDownMenu
==========

![License MIT](https://img.shields.io/badge/Apache-2.0-brightgreen)

## 说明
这是一个非常常用的筛选菜单,使用PopupWindow实现，已在多个项目中完美使用

## 
<img src="https://raw.githubusercontent.com/easyandroid-cc/EasyDropDownMenu/master/images/1616832321436030.gif" width="50%" height="50%"/>


## Installation

Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
         jcenter()
    }
}
```


Add the dependency
```
dependencies {
        implementation 'cc.easyandroid:EasyDropDownMenu:1.1.2'
}
```
## How To Use

layout 文件
```
<cc.easyandroid.menu.widget.EasyDropDownGroup
        android:id="@+id/easyDropDownMenuContainer"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        android:orientation="horizontal">

        <cc.easyandroid.menu.widget.CommonEasyDropDownMenu
            android:id="@+id/menuFilter1"
            android:layout_width="0dp"
            android:layout_height="40dp"
            android:layout_weight="1"
            android:background="@color/q_ffffff"
            android:clickable="true"
            android:saveEnabled="true"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:menuTitle="單列單選"
            app:menuTitleView="@layout/menu_title_layout1" />
            
            ...
 
    </cc.easyandroid.menu.widget.EasyDropDownGroup>
```

 
```java

public class MainFragment extends Fragment {

    EasyDropDownMenu easyDropDownMenu1;
    EasyDropDownMenu easyDropDownMenu2;
    EasyDropDownMenu easyDropDownMenu3;
    EasyDropDownMenu easyDropDownMenu4;
    PopEasyDropDownMenu easyDropDownMenu5;
    PopEasyDropDownMenu easyDropDownMenu6;
    ViewGroup container;
    EasyDropDownGroup easyDropDownGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        easyDropDownGroup = view.findViewById(R.id.easyDropDownMenuContainer);
        easyDropDownMenu1 = view.findViewById(R.id.menuFilter1);
        easyDropDownMenu2 = view.findViewById(R.id.menuFilter2);
        easyDropDownMenu3 = view.findViewById(R.id.menuFilter3);
        easyDropDownMenu4 = view.findViewById(R.id.menuFilter4);
        easyDropDownMenu5 = view.findViewById(R.id.menuFilter5);
        easyDropDownMenu6 = view.findViewById(R.id.menuFilter6);

        container = view.findViewById(R.id.container);
        easyDropDownGroup.setMenuContentContainer(container);

        easyDropDownMenu1.setMenuContentView(new SingleListMenuContentLayout(getActivity()));
        easyDropDownMenu2.setMenuContentView(new MultiSelectSingleRowMenuContentLayout(getActivity()));
        easyDropDownMenu3.setMenuContentView(new MultiSelectTowRowMenuContent1(getActivity()));
        easyDropDownMenu4.setMenuContentView(new ComplexMenuContentLayout2(getActivity()));
        easyDropDownMenu5.setMenuContentView(new ComplexMenuContentLayout2(getActivity()));
        easyDropDownMenu6.setMenuContentView(new SingleListMenuContentLayout(getActivity()));
    }
}
```

License
-------

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


