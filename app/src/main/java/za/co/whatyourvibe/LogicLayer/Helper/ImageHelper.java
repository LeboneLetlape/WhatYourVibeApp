package za.co.whatyourvibe.LogicLayer.Helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageHelper {
    public static Bitmap ConvertUrlToBitmap(Context context, Uri uri) throws IOException {
        return MediaStore.Images.Media.getBitmap(context.getContentResolver(),uri);
    }

    public static Bitmap ConvertDrawableToBitmap(Context context, int Res)
    {
        return BitmapFactory.decodeResource(context.getResources(),Res);
    }

    public static String FileName(Uri uri)
    {
        String _StringLastPathSegment,_StringExtension,_StringFileName;
        _StringLastPathSegment = uri.getLastPathSegment();
        _StringExtension = _StringLastPathSegment.substring(_StringLastPathSegment.lastIndexOf("."));
        _StringFileName = new SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault()).format(new Date());
        return _StringFileName + _StringExtension;
    }

    public static byte [] ConvertBitmapToByteArray(Bitmap bitmap)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public static int[] convertToIntArray(byte[] input)
    {
        int[] ret = new int[input.length];
        for (int i = 0; i < input.length; i++)
        {
            ret[i] = input[i] & 0xff; // Range 0 to 255, not -128 to 127
        }
        return ret;
    }
}
