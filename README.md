# ScrollablePanel
---

A clone version of [ScrollablePanel](https://github.com/Kelin-Hong/ScrollablePanel). Instead of pinning first column of each row, a vertical recyclerview is used.

A flexible view for providing a limited rect window into a large data set, just like a two-dimensional RecyclerView.

It different from RecyclerView is that it's two-dimensional(just like a Panel) and it pin the itemView of first row and first column in their original location.

[ScrollablePanel Demo](http://i.imgur.com/9p8VpLJ.gifv)

## Download ##

Step 1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Step 2. Add the dependency in your build.gradle
```
dependencies {
	        compile ('com.github.PoePoeMyintSwe:ScrollablePanel:v1.0.8'){
	          exclude group: 'com.android.support'
	        }
	}
```

## Usage ##
ScrollablePanel is very similar to the RecyclerView and we can use them in the same way.

####1. Initialize ScrollablePanel
```xml
<ninja.poepoe.library.ScrollablePanelView
      android:id="@+id/scrollable_panel"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      />
```

####2. Adapter

This adapter must extend a class called PanelAdapter,We now have to override following methods so that we can implement our logic.
```java
public class TestPanelAdapter extends PanelAdapter {
    private List<List<String>> data;

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return data.get(0).size();
    }

    @Override
    public int getItemViewType(int row, int column) {
        return super.getItemViewType(row, column);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int row, int column) {
        String title = data.get(row).get(column);
        TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
        titleViewHolder.titleTextView.setText(title);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TestPanelAdapter.TitleViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_title, parent, false));
    }

    private static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;

        public TitleViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(R.id.title);
        }
    }
}
```
####3. Set Adapter
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
   ...
   ...
   TestPanelAdapter testPanelAdapter = new TestPanelAdapter();
   ScrollablePanelView scrollablePanel = (ScrollablePanelView) findViewById(R.id.scrollable_panel);
   scrollablePanel.setAdapter(testPanelAdapter);
   ...
   ...
}
 ```

## License
   ```
    Copyright 2016 Poe Poe Myint Swe

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

   ```