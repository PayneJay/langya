package jack.demo.activity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import jack.demo.JackBaseActivity;
import jack.demo.R;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

/**
 * Destriptions:
 * Created by weipengjie on 16/8/2.
 */
public class ScrollTableViewActicity extends JackBaseActivity {
    private final static int COLUMN_CNT = 45; //显示多少列，这个要和layout文件里面对应起来

    @Override
    protected void init() {
        GridView dataGridView = (GridView) findViewById(R.id.data_gridview);
        dataGridView.setNumColumns(COLUMN_CNT);//一共45列

        dataGridView.setAdapter(new CellAdapter(getApplicationContext()));
        dataGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                makeText(ScrollTableViewActicity.this, "" + position, LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.scroll_table_layout;
    }

    class CellAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        CellAdapter(Context c) {
            mInflater = LayoutInflater.from(c);
        }

        public int getCount() {
            int disp_rows = 20;
            return (disp_rows * COLUMN_CNT);  //行数x列数为一共要显示多少个格子
        }

        public Object getItem(int position) {
            return null; //do nothing now
        }

        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.imagecell, null);
            }

            imageView = (ImageView) convertView.findViewById(R.id.cell_image); //如果直接使用ImageView,这一行不要
            imageView.setBackgroundColor(Color.WHITE);
            if (position % 2 == 0) {
                imageView.setImageResource(R.drawable.ctrip);
            } else {
                imageView.setImageResource(R.drawable.qunar);
            }
            imageView.refreshDrawableState();

            return convertView;
        }

        private int getRow(int position) {
            return (position / COLUMN_CNT);
        }

        private int getColumn(int position) {
            return (position % COLUMN_CNT);
        }
    }
}
