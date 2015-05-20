package org.richinet.ExifViewerPlugin;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.openide.loaders.DataNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;

/**
 *
 * @author Richard Eigenmann
 */
public class JpgDataNode extends DataNode {

    //private final JpgDataObject jpgDataObject;

    public JpgDataNode( JpgDataObject jpgDataObject ) {
        super( jpgDataObject, Children.LEAF );
    }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = super.createSheet();

        try {
            JpgDataObject jpgDataObject =getLookup().lookup(JpgDataObject.class);

            Metadata metadata = ImageMetadataReader.readMetadata( jpgDataObject.getPrimaryFile().getInputStream() );
            for ( Directory directory : metadata.getDirectories() ) {
                Sheet.Set directorySheet = Sheet.createPropertiesSet();
                directorySheet.setName( directory.getName() );
                directorySheet.setDisplayName( directory.getName() );
                sheet.put( directorySheet );
                for ( final Tag tag : directory.getTags() ) {
                    Property<String> tagProperty
                            = new PropertySupport.ReadOnly<String>( tag.getTagName(), String.class, tag.getTagName(), tag.getTagName() ) {

                                @Override
                                public String getValue() throws IllegalAccessException, InvocationTargetException {
                                    return ( tag.getDescription() );
                                }
                            };
                    directorySheet.put( tagProperty );
                }

            }
        } catch ( ImageProcessingException | IOException ex ) {
            System.out.println( ex.getMessage() );
            Exceptions.printStackTrace( ex );
        }

        return sheet;

    }

}
