package jack.demo.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import jack.demo.R;

/**
 * Destriptions:
 * Created by weipengjie on 16/8/2.
 */
public class ScrollTableViewActicity extends Activity {
    private int disp_rows = 20;  //显示多少行
    private final static int COLUMN_CNT = 45; //显示多少列，这个要和layout文件里面对应起来

    private GridView dataGridView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scroll_table_layout);
        dataGridView = (GridView) findViewById(R.id.data_gridview);
        dataGridView.setNumColumns(COLUMN_CNT);//一共45列

        dataGridView.setAdapter(new CellAdapter(getApplicationContext()));
        dataGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(ScrollTableViewActicity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });

    } //end onCreate

    class CellAdapter extends BaseAdapter {
        private Context mContext;
        private LayoutInflater mInflater;

        public CellAdapter(Context c) {
            mContext = c;
            mInflater = LayoutInflater.from(c);
        }

        public int getCount() {
            return (disp_rows * COLUMN_CNT);  //行数x列数为一共要显示多少个格子
        }

        public Object getItem(int position) {
            return null; //do nothing now
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        // ImageView 放在了自定义的格子排版文件中，可以扩展使用，也就是说，格子显示的内容可以自己扩展
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int row = getRow(position); //获取该格子对应表格的行和列
            int column = getColumn(position);

            ImageView imageView;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.imagecell, null);

                //这里也可以不用自己定义的imagecell排版，而直接使用如TextView 或 ImageView等作为一个格子的显示，这里演示自定义排版是为了扩展使用

                //imageView = new ImageView(mContext);  //直接使用ImageView
            }  //重用View，提高性能 else  {

            // imageView = (ImageView) convertView;   //直接使用ImageView时
            imageView = (ImageView) convertView.findViewById(R.id.cell_image); //如果直接使用ImageView,这一行不要
            imageView.setBackgroundColor(Color.WHITE);
            if (position%2 == 0){
                imageView.setImageResource(R.drawable.ctrip);
            }else{
                imageView.setImageResource(R.drawable.qunar);
            }
            imageView.refreshDrawableState();

            return convertView;
        }

        private final int getRow(int position) {
            return (position / COLUMN_CNT);
        }

        private final int getColumn(int position) {
            return (position % COLUMN_CNT);
        }
    }
}
