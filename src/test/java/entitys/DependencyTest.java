package entitys;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;


public class DependencyTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private SubDependency subDependency;

    private Dependency dependency;

    @Before
    public void setupMock() {
        subDependency = mock(SubDependency.class);
        dependency = new Dependency(subDependency);
    }

    @Test
    public void tesSubDependency() {
        when(subDependency.getClassName()).thenReturn("hi there");

        assertEquals("hi there", subDependency.getClassName());
    }

    @Test()
    public void testException() {
        when(subDependency.getClassName()).thenThrow(IllegalArgumentException.class);

        dependency.getClassName();
    }

    @Test
    public void testAddTwo(){
        when(dependency.addTwo(1)).thenReturn(5);

        assertEquals(5, dependency.addTwo(1));
        assertEquals(0, dependency.addTwo(27));
    }

    @Test
    public void testAddTwoAny(){
        when(dependency.addTwo(anyInt())).thenReturn(0);

        assertEquals(0, dependency.addTwo(3));
        assertEquals(0, dependency.addTwo(80));
    }


    @Test
    public void testSimpleVerify(){
        //Nunca se ha ejecutado
        verify(dependency, never()).getClassNameUpperCase();
        dependency.getClassNameUpperCase();
        //Exactamente una vez
        verify(dependency, times(1)).getClassNameUpperCase();
        //Como mínimo una vez
        verify(dependency,atLeast(1)).getClassNameUpperCase();
        dependency.getClassNameUpperCase();
        //Como máximo 2 veces
        verify(dependency, atMost(2)).getClassNameUpperCase();
    }

    @Test
    public void testParameters(){
        dependency.addTwo(3);
        //una Vez con el parámetro 3
        verify(dependency, times(1)).addTwo(3);
        dependency.addTwo(4);
        //dos veces con cualquier parámetro
        verify(dependency, times(2)).addTwo(anyInt());
    }

}